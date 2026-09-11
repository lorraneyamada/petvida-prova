# RESPOSTAS — Prova Prática PetVida

NN = 02

## Parte B

(já preenchida — ver tabela e B.1 no enunciado/versão que você já escreveu)

## Parte C — Depuração

### Quadro de itens

| Item | É defeito? | Sintoma observado — mensagem literal | Causa | Correção aplicada |
|---|---|---|---|---|
| 1 | Sim | `Whitelabel Error Page`<br>`This application has no explicit mapping for /error, so you are seeing this as a fallback.`<br>`Thu Sep 10 21:48:18 BRT 2026`<br>`There was an unexpected error (type=Not Found, status=404).` | A classe `ConsultaController` não tinha a anotação `@Controller`. Sem ela, o Spring não reconhece a classe como bean gerenciado, então o `@Autowired` não é processado e o `@GetMapping` nunca é registrado no `DispatcherServlet`. | Adicionada a anotação `@Controller` acima da classe. |
| 2 | Não | — | O Spring normaliza internamente padrões de `@GetMapping` sem barra inicial, adicionando a barra antes de registrar a rota. | Nenhuma correção necessária. |
| 3 | Sim | Navegador: `Whitelabel Error Page` / `Thu Sep 10 22:43:13 BRT 2026` / `There was an unexpected error (type=Internal Server Error, status=500).`<br>Console: **[COLAR AQUI as ~10 primeiras linhas do console a partir de "Exception" — você já tem a aplicação rodando com o defeito reproduzido agora]** | O Controller registrava o objeto no Model com a chave `"bicho"`, mas o template usa `${animal.nome}`. Sem uma variável `animal` no contexto, o Thymeleaf não consegue avaliar a expressão. | Trocado `model.addAttribute("bicho", ...)` por `model.addAttribute("animal", ...)`. |
| 4 | Sim | **[COLAR AQUI — ainda não testado: veja passo abaixo]** | O método retornava `"consulta.html"`, mas o Thymeleaf já adiciona sozinho o sufixo `.html`. Com a extensão duplicada, ele procura `consulta.html.html`, que não existe. | Trocado `return "consulta.html";` por `return "consulta";`. |
| 5 | Sim | Nenhum erro é lançado; a página carrega, mas exibe literalmente `${animal.especie}` em vez do valor. | A tag `<p>` não usa `th:text`. Sem atributo `th:*`, o Thymeleaf trata o conteúdo como texto estático e não avalia a expressão. | Adicionado `th:text="${animal.especie}"` na tag. |

### D.1

Duas capturas em `evidencias/`: `item1_404.png` (erro 404, rota nunca registrada por falta de `@Controller`) e `item3_spel_error.png` (erro 500, chave errada no Model) — dois sintomas diferentes, ambos com o defeito ainda presente.

Saída de `git log --oneline`:
```
4f19bbf (HEAD -> main, origin/main) parte-d: defeitos corrigidos
3518c4e parte-d: código com defeitos
ac2a9ab parte-d: código com defeitos
9c55ce2 wip: parte b
297a9cd wip: evidencias A.7
0d0a76a parte-a: sistema funcionando
5a65469 parte-a: projeto configurado
```

### D.2

A linha com `th:text="${animal.nome}"` é processada pelo Thymeleaf porque `th:text` é um atributo reconhecido pelo motor de templates: ele avalia a expressão no Model e substitui o conteúdo da tag pelo valor obtido. Já a linha `<p>${animal.especie}</p>` (Item 5) não usa nenhum atributo `th:*`, então o Thymeleaf ignora esse trecho e o trata como texto estático do HTML, exibindo literalmente os caracteres `${animal.especie}` na tela. Isso mostra que o Thymeleaf só processa expressões dentro de atributos `th:*`; fora disso, o conteúdo é texto puro.

### D.3

O Item 2 (`@GetMapping("consulta")`, sem barra inicial) não é defeito porque, ao testar `http://localhost:8080/consulta`, a rota respondeu normalmente (depois de corrigidos os outros itens). Isso confirma, na prática, que o Spring normaliza internamente padrões de mapeamento sem barra inicial antes de registrá-los.

---

## [restante das partes — D a H - não deu tempo]
