# 🥩 Chuleta Práctica EE2 — proyecto Maven ejecutable

Un paquete por tema del examen. **Todo el conocimiento está en los comentarios del código** — léelos en este orden:

| Paquete | Qué aprende | Clase → Test |
|---|---|---|
| `ee2.cajablanca` | Grafo de flujo, V(G) por las 3 fórmulas, 1 test por camino independiente | `GestorReservasSalas` → `GestorReservasSalasTest` |
| `ee2.cajanegra` | Partición de equivalencia (tabla de directrices), casos válidos/no válidos, valores límite | `ValidadorRegistroEmpleado` → `ValidadorRegistroEmpleadoTest` |
| `ee2.mocks` | Plantilla Mockito completa: @Mock, when/thenReturn, assertThrows, verify/never/times | `DispensacionService` → `DispensacionServiceTest` |
| `ee2.mutacion` | PIT: mutantes vivos/muertos, mutation score, valores límite que matan mutantes | `DiscountCalculator` → `DiscountCalculatorTest` |

## Abrir en IntelliJ IDEA

`File → Open →` selecciona **este `pom.xml`** → `Open as Project`. Espera a que Maven sincronice (descarga JUnit/Mockito/PIT la primera vez — necesita internet).

## Comandos (terminal de IntelliJ)

```bash
mvn test                                        # corre las 26 pruebas (todas verdes)
mvn clean package test                          # el comando que usó el profesor en clase
mvn test-compile org.pitest:pitest-maven:mutationCoverage   # pruebas de mutación con PIT
```

> ⚠️ Matiz importante: el goal de PIT **no recompila** — usa los `.class` ya generados en `target/`.
> Si cambias un test y corres solo `pitest:mutationCoverage`, PIT evalúa la versión VIEJA.
> Por eso el comando de arriba incluye `test-compile` (o corre `mvn clean test` antes).

El reporte de PIT queda en **`target/pit-reports/index.html`** (ábrelo en el navegador: verde = mutante muerto, rojo = vivo).

> Si `mvn` no se reconoce en una terminal externa: usa la terminal DE IntelliJ, o agrega a tu PATH el Maven que trae IntelliJ:
> `C:\Program Files\JetBrains\IntelliJ IDEA 2026.1.1\plugins\maven\lib\maven3\bin` (así lo enseñó el jefe de práctica).

## 🧪 El experimento que te hace "sentir" la mutación (2 min, verificado)

1. Corre `mvn test-compile org.pitest:pitest-maven:mutationCoverage` → **10/10 mutantes muertos (100%)**.
2. Abre `DiscountCalculatorTest` y **comenta el test `limite_precioCero_esValidoYDevuelveCero`**.
3. Corre `mvn clean test-compile org.pitest:pitest-maven:mutationCoverage` → ahora **9/10 (90%): sobrevive un `CONDITIONAL BOUNDARY`** (cambió `<` por `<=` en `originalPrice < 0`). Tus 6 tests restantes "pasan" pero ninguno distingue si el precio 0 es válido o rechazado.
4. Descomenta el test → 100% otra vez. Es exactamente lo que le pasó al profesor en la demo de clase (le faltaba probar precio = 0). **Moraleja: siempre probar los valores límite.**

## Mapa mental de 30 segundos

- **Caja blanca/negra** = cómo DISEÑO los casos (mirando el código / mirando los requisitos).
- **Mockito** = cómo AÍSLO la clase si tiene dependencias (Repository → @Mock). Sin dependencias, no hay mocks.
- **PIT/mutación** = qué tan BUENAS quedaron mis pruebas (score = muertos / (total − equivalentes)).
- En el examen: te dan código sin casos → **V(G) caminos = mínimo de tests**, y **hay que dibujar el grafo**.
