\# PetVida — Prova Prática



\*\*Nome:\*\* Lorrane Yamada

\*\*NN:\*\* 02



\---



\## A.7 — Evidências de execução



\### Ficha do animal (/ficha\_02)

!\[Ficha](evidencias/ficha\_02.png)



\### Ficha do tutor (/tutor\_02)

!\[Tutor](evidencias/tutor\_02.png)



\### Resumo da clínica (/resumo\_02)

!\[Resumo](evidencias/resumo\_02.png)



\### Console H2 — SELECT do tutor e animal da semente

!\[H2 Console](evidencias/h2-console.png)





B.1 — No meu sistema, os métodos ficha() e resumo() estão mapeados nas URLs /ficha\_02 e /resumo\_02, ambos usando o mesmo verbo HTTP (GET). Como o verbo é idêntico nos dois casos, é a URL que o Spring usa para decidir qual método deve atender a requisição. O verbo sozinho não bastaria porque ele só indica o tipo de operação (leitura, no caso do GET) — não diz qual recurso está sendo solicitado; sem a URL, o Spring não teria como diferenciar uma requisição para a ficha do animal de uma para o resumo da clínica.



\# PetVida — Prova Prática



\*\*Nome:\*\* Lorrane Yamada

\*\*NN:\*\* 02



\---



\## A.7 — Evidências de execução



\### Ficha do animal (/ficha\_02)

!\[Ficha](evidencias/ficha\_02.png)



\### Ficha do tutor (/tutor\_02)

!\[Tutor](evidencias/tutor\_02.png)



\### Resumo da clínica (/resumo\_02)

!\[Resumo](evidencias/resumo\_02.png)



\### Console H2 — SELECT do tutor e animal da semente

!\[H2 Console](evidencias/h2-console.png)



\---



\## Parte B — Planejamento da camada de controle



| Ação | Tipo de requisição | URL completa | Mapeamento (anotação) | Template |

| Mostrar a ficha do animal | GET | http://localhost:8080/ficha\_02 | @GetMapping("/ficha\_02") | ficha.html |

| Mostrar a ficha do tutor | GET | http://localhost:8080/tutor\_02 | @GetMapping("/tutor\_02") | tutor.html |

| Mostrar o resumo da clínica | GET | http://localhost:8080/resumo\_02 | @GetMapping("/resumo\_02") | resumo.html |

| Cadastrar um novo animal | não existe | não existe | não existe | não existe |



\*\*B.1\*\* — No meu sistema, os métodos ficha() e resumo() estão mapeados nas URLs /ficha\_02 e /resumo\_02, ambos usando o mesmo verbo HTTP (GET). Como o verbo é idêntico nos dois casos, é a URL que o Spring usa para decidir qual método deve atender a requisição. O verbo sozinho não bastaria porque ele só indica o tipo de operação (leitura, no caso do GET) — não diz qual recurso está sendo solicitado; sem a URL, o Spring não teria como diferenciar uma requisição para a ficha do animal de uma para o resumo da clínica.

