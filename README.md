# Deadalus IDE for IntelliJ Platform
Provides IDE support for Daedalus language, dedicated for gameplay scripting in games on zEngin engine (Gothic I, II, II:NOTR).
This project was developed primarily for SoulFire group.

## Status
Early work in progress. Parser was finished quite a while ago and works with all original scripts.
On the other hand, reference resolver is very basic - just matches names, ignoring scoping rules and order of module sources in its .src file.

## Usage
Install IntelliJ IDEA CE, open `File > Settings > Plugins > Install plugin from disk`, choose file `daedalus-intellij.jar` from root of this repository.
To open project, use `File > New > Project from Existing Sources...`.

## Contribution
### Generating parser sources
To develop plugin, you will need to manually generate parser sources once.
It will be more times if parser is going to change, but it should be rather rare.
All parser sources are generated from file `resources/org/avallach/daedalus/parser/Parser/Daedalus.bnf`.
Intermediate file `_DaedalusLexer.flex` is used for generated lexer definition.

1. `Daedalus.bnf > Tools > Generate Parser Code`
2. `Daedalus.bnf > Tools > Generate JFlex Lexer > gen/org/avallach/daedalus/parser/_DaedalusLexer.flex`
3. `_DaedalusLexer.flex > Tools > Run JFlex Generator`

### Building the plugin .jar
`Build > Prepare Plugin Module daedalus-intellij For Deployment`

# Contact
- mailto://github**@**avallach.ovh
- http://themodders.org/index.php?topic=27411
