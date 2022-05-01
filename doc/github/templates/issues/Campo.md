## Definición de la tarea

Se necesita un campo para la Entidad `Tarea` para guardar si la tarea es una tarea destacada con las siguientes características:

- Tipo: `[FieldType]`
- Obligatorio:`[true/false]`
- Nombre `[fieldName]`
- Default: `[default_value]`

## Lógica

[Qué tiene que hacer el campo]

## Pasos

- [ ] Actualizar la rama `epic/[epic_id]` en local (`git checkout epic/[epic_id] && git pull origin epic/[epic_id]`).
- [ ] A partir de la rama `epic/[epic_id]`, crear una rama que se llame `feature/[issue_id]` (`git checkout -b feature/[issue_id]`).
- [ ] **Desarrollar la funcionalidad teniendo en cuenta lo anterior.**
- [ ] Hacer un commit de la rama `feature/[issue_id]` (El mensaje debe contener el texto `closes #[issue_id]`)
- [ ] Hacer un merge con `epic/[epic_id]` (`git checkout epic/[epic_id] && git merge feature/[issue_id]`).
- [ ] Subir la rama `epic/[epic_id]` (`git push origin epic/[epic_id]`)
