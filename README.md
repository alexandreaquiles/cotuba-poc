# Cotuba

O Cotuba é uma aplicação de linha de comando (CLI) implementada em Java que transforma arquivos Markdown (.md) em ebooks nos formatos PDF ou EPUB.

Cada arquivo `.md` é considerado um capítulo diferente. O título do capítulo é extraído do maior _heading_: o `#` no Markdown.

Olhando um pouquinho mais de perto, o Cotuba faz o seguinte:
- pega parâmetros do usuário
- lê cada `.md`, faz o parse e os renderiza para HTML
- gera o PDF ou EPUB, de acordo com os parâmetros

## Pré-requisitos

- Java 9
- Maven 3

## Instalando

1. Ir até `cotuba-cli` e executar `mvn install`
2. Ir até `tema-paradizo` e executar `mvn package`
3. Copiar o ZIP de `cotuba-cli/target`
4. Copiar o ZIP de `tema-paradizo/target`
5. Descompactar os ZIPs em alguma pasta, fazendo o merge da pasta `libs`
6. Executar `./cotuba.sh`

## Opções

Ao invocar o `./cotuba.sh`, você pode usar:

- a opção `-d`, para indicar o diretório onde estão os arquivos `.md`. Por padrão, será usado o diretório atual.
- a opção `-f`, para indicar o formato. Pode ser `epub` ou `pdf` (o padrão). 
- a opção `-o`, para indicar o nome do arquivo de saída. O padrão será `book.epub` ou `book.pdf`, de acordo com o formato.
