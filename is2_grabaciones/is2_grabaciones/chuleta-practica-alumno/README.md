# 🏋️ Chuleta Práctica EE2 — VERSIÓN ALUMNO (plantillas para que TÚ implementes)

Mismo proyecto que `chuleta-practica`, pero **sin las respuestas**: las clases de producción vienen como te las darían en el examen y los tests son plantillas con instrucciones. La versión resuelta (`..\chuleta-practica`) es tu **solucionario** — no lo mires hasta terminar cada ejercicio.

## Cómo empezar

`File → Open →` este `pom.xml` → `Open as Project`. Los ejercicios 1-3 arrancan **en rojo** (un `fail("TODO...")` te recuerda lo que falta): tu misión es implementarlos y borrar el placeholder. El 4 arranca en verde... y esa es justamente la trampa.

## Los 4 ejercicios (en orden, ~20-25 min c/u — como en el examen)

| # | Paquete | Qué practicas | Termina cuando |
|---|---|---|---|
| 1 | `ee2.cajablanca` | Grafo en papel → V(G) por 3 fórmulas → caminos → 1 test por camino | `mvn test` verde con ≥ V(G) tests |
| 2 | `ee2.cajanegra` | Tabla de clases de equivalencia → casos válidos/no válidos → valores límite | `mvn test` verde cubriendo todas las clases |
| 3 | `ee2.mocks` | Clase de test Mockito completa: anotaciones, stubbing, assertThrows, verify/never | `mvn test` verde con los incisos (a)-(e) |
| 4 | `ee2.mutacion` | PIT: encontrar el mutante vivo, entender por qué vive, matarlo | PIT reporta 100% (10/10 muertos) |

## Comandos

```bash
mvn test                                                    # ejercicios 1-3
mvn test-compile org.pitest:pitest-maven:mutationCoverage   # ejercicio 4 (reporte: target/pit-reports/index.html)
```

> Si `mvn` no se reconoce: usa la terminal de IntelliJ, o agrega al PATH:
> `C:\Program Files\JetBrains\IntelliJ IDEA 2026.1.1\plugins\maven\lib\maven3\bin`

## Reglas del juego

1. **Papel primero** (ejercicios 1-2): en el examen el grafo y las tablas se entregan — practica dibujarlos.
2. **No mires el solucionario** (`..\chuleta-practica`) hasta que tu `mvn test` esté verde: comparar DESPUÉS es donde más se aprende (¿te faltó el camino de 0 iteraciones? ¿el `never()`? ¿el valor límite?).
3. Cronométrate: ~25 min por ejercicio es el ritmo del examen real.
