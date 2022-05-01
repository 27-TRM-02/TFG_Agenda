## Comportamiento Actual

[Comportamiento actual]

## Comportamiento Esperado

[Comportamiento esperado]

## Pasos

- [ ] Actualizar la rama `dev` en local (`git checkout dev && git pull origin dev`).
- [ ] A partir de la rama `dev`, crear una rama que se llame `bug/[issue_id]` (`git checkout -b bug/[issue_id]`).
- [ ] **Desarrollar la funcionalidad teniendo en cuenta lo anterior.**
- [ ] Hacer un commit de la rama `bug/[issue_id]` (El mensaje debe contener el texto `closes #[issue_id]`)
- [ ] Hacer un merge con `dev` (`git checkout dev && git merge bug/[issue_id]`).
- [ ] Subir la rama `dev` (`git push origin dev`)
