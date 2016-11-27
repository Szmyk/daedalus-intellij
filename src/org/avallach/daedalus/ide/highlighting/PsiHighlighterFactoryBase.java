package org.avallach.daedalus.ide.highlighting;

import com.intellij.codeHighlighting.Pass;
import com.intellij.codeHighlighting.TextEditorHighlightingPass;
import com.intellij.codeHighlighting.TextEditorHighlightingPassFactory;
import com.intellij.codeHighlighting.TextEditorHighlightingPassRegistrar;
import com.intellij.codeInsight.daemon.impl.HighlightInfo;
import com.intellij.codeInsight.daemon.impl.HighlightInfoType;
import com.intellij.codeInsight.daemon.impl.UpdateHighlightersUtil;
import com.intellij.openapi.components.AbstractProjectComponent;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiRecursiveElementVisitor;
import org.avallach.commons.Debug;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class PsiHighlighterFactoryBase extends AbstractProjectComponent implements TextEditorHighlightingPassFactory
{
	protected PsiHighlighterFactoryBase(Project project, TextEditorHighlightingPassRegistrar registrar)
	{
		this(project, registrar, null);
	}
	protected PsiHighlighterFactoryBase(Project project, TextEditorHighlightingPassRegistrar registrar, @Nullable Class<? extends PsiHighlighterFactoryBase> previousPassType)
	{
		super(project);

		registrar.registerTextEditorHighlightingPass
		(
			this,
			previousPassType != null ? new int[]{System.identityHashCode(previousPassType)} : null,
			new int[]{Pass.UPDATE_ALL},
			false,
			System.identityHashCode(this.getClass())
		);
	}

	protected abstract HighlightInfoType getStyle(PsiElement element);

	@Nullable
	@Override
	public TextEditorHighlightingPass createHighlightingPass(@NotNull final PsiFile file, @NotNull final Editor editor)
	{
		return new TextEditorHighlightingPass(file.getProject(), editor.getDocument(), false)
		{
			private final List<HighlightInfo> highlightInfos = new ArrayList<>();
			private final PsiElementVisitor visitor = new PsiRecursiveElementVisitor()
			{
				@Override
				public void visitElement(PsiElement element)
				{
					//Debug.log("TextEditorHighlightingPass#visitor#visitElement", element.getText(), System.currentTimeMillis());
					HighlightInfoType style = PsiHighlighterFactoryBase.this.getStyle(element);
					if (style != null)
						addHighlight(element, style);
					super.visitElement(element);
				}
			};

			private void addHighlight(PsiElement element, HighlightInfoType style)
			{
				highlightInfos.add(HighlightInfo.newHighlightInfo(style)
						.range(element)
						.createUnconditionally());
			}

			@Override
			public void doCollectInformation(@NotNull ProgressIndicator progress)
			{
				highlightInfos.clear();
				file.accept(visitor);
			}

			@Override
			public void doApplyInformationToEditor()
			{
				UpdateHighlightersUtil.setHighlightersToEditor(myProject, editor.getDocument(), 0, file.getTextLength(), highlightInfos, getColorsScheme(), getId());
			}
		};
	}
}