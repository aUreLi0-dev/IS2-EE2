# Especificacion

## Proposito

Validar y analizar solo los cuadrantes que no esten  coonsiserados dentro de una zona de ruido 

### Input

- Quadrante quadrante 

### Precondiciones

- Que al menos una coordenada tenga un valor diferente de 0
- Probabilidad de los cuadrantes no esten en zona de ruido  que no esten en el rango de 0.49  y 0.51
- Que ninguna de las coordenadas este vacia
- Que cada  coordenada este dentro de rango permitido  < -1_000_000 || v > 1_000_000
- Que el cuadrante no este vacio

### Output

- Analisis del resultado obtenido mediante una notificación, en esta se incluye el tipo de alerta si es Universitario o de Tipo NASA la probabilidad y un mensaje final.
### Postcondiciones

- El quadrante no debe tomar las zonas consideradas como zona muertas a pesar que todas las precondiciones esten correctas

### Errores

- El cuadrante tiene todos los valores como 0
- No se puede tomar un cuadrante con una coordenada vacia
-No se puede tomar un cuadrante vacio
-La coordenada no esta dentro del rango permitido

