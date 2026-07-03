## Tipos de antipatrones Smells y justificación
# #1 Longer Function:En la función procesar ticket se tiene varias lineas de codigo las cuales producen una alta complejidad ciclomatica por ejemplo public TicketOutcome processTicket(Ticket t, String agentId, InMemoryNotifier notifier) 
          double baseUsd = 0.0;
        if (customer.equals("FREE")) {
            if (category.equals("BUG")) baseUsd = 5.0;
            else if (category.equals("FEATURE")) baseUsd = 8.0;
            else if (category.equals("BILLING")) baseUsd = 6.0;
            else baseUsd = 7.0;

            if (severity.equals("LOW")) baseUsd += 1.0;
            else if (severity.equals("MEDIUM")) baseUsd += 3.0;
            else if (severity.equals("HIGH")) baseUsd += 6.0;
            else if (severity.equals("CRITICAL")) baseUsd += 10.0;
            else baseUsd += 2.0
        } else if (customer.equals("PRO"))
            if (category.equals("BUG")) baseUsd = 12.0;
            else if (category.equals("FEATURE")) baseUsd = 15.0;
            else if (category.equals("BILLING")) baseUsd = 10.0;
            else baseUsd = 11.0;

            if (severity.equals("LOW")) baseUsd += 2.0;
            else if (severity.equals("MEDIUM")) baseUsd += 4.0;
            else if (severity.equals("HIGH")) baseUsd += 8.0;
            else if (severity.equals("CRITICAL")) baseUsd += 15.0;
            else baseUsd += 3.0;

# 2 Class in the function: En la funcion de procesar ticket tambien se encontro varias variables las cuales se definen dentro de la misma funcion para poder realizar las validaciones y los casos de entrada de salida de cada función 
       String customer = t.getCustomerType().trim().toUpperCase(Locale.ROOT);
        String category = t.getCategory().trim().toUpperCase(Locale.ROOT);
        String severity = t.getSeverity().trim().toUpperCase(Locale.ROOT);
        String channel  = t.getChannel().trim().toUpperCase(Locale.ROOT);


# 3 Big Class: En algunas clases como Ticket o TicketOutCome se puede apreciar que se definen mediante varios atributos los cuales se podrian dividr en varias clases o se podrian reducir estos atributos y ponerlos en una clase mas resumida: por esto el codigo s evuelve menos legible y tener una clase muy grande se vuelve una mala practica al momento de definir clases o construir cada una de estas clases las cuales almecenan data.

public class Ticket 
    private final String id;
    private final String customerType; // "FREE", "PRO", "ENTERPRISE"
    private final String category;     // "BUG", "FEATURE", "BILLING"
    private final String severity;     // "LOW", "MEDIUM", "HIGH", "CRITICAL"
    private final String channel;      // "EMAIL", "CHAT", "PHONE"
    private final Instant createdAt;
    private String status;             // "OPEN", "IN_PROGRESS", "ESCALATED", "CLOSED"
 private final String ticketId;
    private final double priceUsd;
    private final double pricePen;
    private final long slaTargetMinutes;
    private final boolean slaBreached;
    private final String finalStatus;
## Como se planeo solucionar  

Metodo uno: Se puede hacer el function method el cual lo que hace es poner todo un bloque de codigo que se encuentra en una misma  parte del codigo y dividirlo en varias funciones, con este metodo cada accion o funcionalidad se puede manejar de distintas formas a lo largo de la vida del software y hace al codigo mas legible y mantenible, se puede hacer varias funciones que tengan o sean para cada tipo de cliente  y asi reducir la complejidad ciclomatica de cada archivo.

Metodo dos: Variable Class : en la funcion de TicketOutcome processTicket, es decir procesar ticketse se tiene varias variables o atributos que se definen dentro de la función se puede tomar todos estos atributos o variables y ponerlo en otra clase llamada  cliente   
 y llamar a esta misma clase en esta solución se manda un mensaje a una sola clase y se puede modificar esta misma de acuerdo a las especificaciones del cliente o la cantidad de clases o mensajes que se quiere tener al momento de la creacion del ticket.

Metodo tres: Divide Class: Se podria dividir las clases de TicketOutcome o la clase Ticket en varias clases con la finalidad de reducir la complejidad ciclomatica en una sola funxcion como en la de procesar ticket y nada mas llamar a estaas clases con sus metodos los cuales se encargaran de llamar o calcular los metodos calculados de la clase dividida.