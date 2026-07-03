# Hoja de Respuestas - Laboratorio 10

**Nombre:** Jefferson Angelo Sanchez Palacios
**Código:** 20235218

---

## Ejercicio 1: Caja Blanca (Evaluador de Créditos)

### 1.1) Cálculo de Complejidad Ciclomática
*(Escribe aquí tus cálculos usando las fórmulas de Nodos/Aristas o Predicados)*

- **Fórmula:** (Ej: Complejidad Ciclomática (V(G)) = Nodos de Decisión + 1)
  - Nodos de Decisión:
    - edad < 18?
    - salario < 1000?
    - tieneDeudas?
    - salario < 2000?
    - monto > salario * 3?
  - Son 5 nodos de decisión.
- **Resultado:**  5 + 1 = 6

### 1.2) Caminos Independientes
*(Lista aquí los caminos independientes encontrados en tu Grafo de Flujo según tu cálculo anterior)*

- Camino 1: Inicio -> B(Sí) -> Retorna RECHAZADO_EDAD *(Ej: edad = 17)*
- Camino 2: Inicio -> B(No) -> C(Sí) -> Retorna RECHAZADO_SALARIO *(Ej: salario = 999)*
- Camino 3: Inicio -> B(No) -> C(No) -> D(Sí) -> E(Sí) -> Retorna RECHAZADO_DEUDAS *(Ej: salario = 1000)*
- Camino 4: Inicio -> B(No) -> C(No) -> D(Sí) -> E(No) -> F(Sí) -> Retorna RECHAZADO_MONTO *(Ej: salario = 2000 monto = 6001)*
- Camino 5: Inicio -> B(No) -> C(No) -> D(Sí) -> E(No) -> F(No) -> Retorna APROBADO *(Ej: salario = 3000 monto = 5000)*
- Camino 6: Inicio -> B(No) -> C(No) -> D(No) -> F(Sí) -> Retorna RECHAZADO_MONTO *(Ej: salario = 3000 monto = 9001)*

## Ejercicio 2: Caja Negra (Validador de Registro)

### 2.1) Tabla de Clases de Equivalencia

| Campo | Clases de Equivalencia Válidas                           | Clases de Equivalencia Inválidas                            |
|-------|----------------------------------------------------------|-------------------------------------------------------------|
| Nombre | Texto no nulo y no vacío después de quitar espacios      | Valor nulo, campo vacío o solo espacios                     |
| Email | Texto no nulo que contiene al menos un `@`                | Valor nulo o texto sin `@`                                  |
| Edad | Número entero mayor o igual a 18                         | Número entero menor de 18                                   |
| Tipo de Documento | Tipo permitido por el sistema: `DNI` o `CE`              | Valor nulo o tipo distinto de `DNI` y `CE`                  |
| Número de Documento | Texto no nulo con exactamente 8 caracteres              | Valor nulo o texto con longitud distinta de 8 caracteres    |

### 2.2) Tabla de Casos de Prueba Válidos (Flujo Ideal)

| ID | Nombre            | Email                   | Edad | Tipo Doc. | Num. Doc. | Resultado Esperado        |
|---|-------------------|-------------------------|------|-----------|----------|---------------------------|
| CP-V-01 | Jefferson Sanchez | jjjangelosss@gmail.com  | 22   | DNI       | 76951422 | true  |

### 2.3) Tabla de Casos de Prueba Inválidos (Flujos de Error)

| ID | Nombre | Email | Edad | Tipo Doc. | Num. Doc. | Resultado Esperado |
|---|--------|-------|------|-----------|-----------|--------------------|
| CP-I-01 |  | juan@test.com | 25 | DNI | 12345678 | false *(Nombre vacío)* |
| CP-I-02 | Maria Lopez | marialopez.test.com | 25 | CE | A1234567 | false *(Email sin `@`)* |

---
*(Recuerda que estas tablas te servirán de base para construir tus pruebas en JUnit 5 dentro del proyecto)*
