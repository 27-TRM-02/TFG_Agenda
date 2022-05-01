## Definición del Endpoint

[Breve descripción del Endpoint].

- Ruta en Spring: `/[entity]/[endpoint]/[path]`
- Ruta en Postman: `/[entity]/[endpoint]/[path]`
- Verbo HTTP: `[METHOD]`
- Tipo de Respuesta: `[ResponseType]`

## Lógica

- GET `/[entity]/[endpoint]/[path]`
- [Si todo va OK]

```json
{
  "[response]": "[Respuesta si todo va OK]"
}
```

- [Si algo va KO]

```json
{
  "[response]": "[Respuesta si algo va KO]"
}
```

## Pasos

- [ ] Actualizar la rama `epic/[epic_id]` en local (`git checkout epic/[epic_id] && git pull origin epic/[epic_id]`).
- [ ] A partir de la rama `epic/[epic_id]`, crear una rama que se llame `feature/[issue_id]` (`git checkout -b feature/[issue_id]`).
- [ ] **Desarrollar la funcionalidad teniendo en cuenta lo anterior.**
- [ ] Hacer un commit de la rama `feature/[issue_id]` (El mensaje debe contener el texto `Closes #[issue_id]`)
- [ ] Hacer un merge con `epic/[epic_id]` (`git checkout epic/[epic_id] && git merge feature/[issue_id]`).
- [ ] Subir la rama `epic/[epic_id]` (`git push origin epic/[epic_id]`)
