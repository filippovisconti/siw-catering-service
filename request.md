Dobbiamo progettare e implementare il sistema informativo di una società che offre servizi di catering.

La società offre diversi buffet: 
ogni buffet è 
    proposto da uno chef e 
    contiene uno o più piatti.
Ogni buffet 
    ha un nome e 
    una descrizione. 

Uno chef può 
    proporre uno o più buffet. 
Per ogni chef sono di interesse 
    nome, cognome, nazionalità.


Per ogni piatto proposto in un buffet sono di interesse 
    il nome, 
    una descrizione, 
    l'elenco degli ingredienti. 

Per ogni ingrediente è di interesse 
    il nome, 
    l'origine, 
    una descrizione.

Le specifiche possono essere completate dal candidato, laddove ritenuto necessario.

Possono accedere al sistema 
    utenti generici e 
    un amministratore.

L’amministratore, previa autenticazione, può 
    inserire, 
    modificare, 
    cancellare le informazioni relative 
        ai buffet, 
        agli chef, 
        ai piatti

L’utente generico può accedere alle informazioni della società 
    attraverso diversi percorsi di navigazione, opportunamente predisposti (ad esempio, per chef, oppure per buffet, etc.)

Progettare il sistema, definendone casi d’uso, class diagram (con indicazioni utili alla progettazione dello strato di persistenza).
Implementare almeno 4 casi d’uso:

    almeno due che abbiamo come attore l’amministratore (almeno uno di inserimento dati ed almeno uno di aggiornamento dati)

    almeno due che abbiano come attore l’utente generico

Implementare il sistema con Spring Boot. Saranno oggetto di valutazione anche la qualità del codice e la qualità dell’interfaccia HTML+CSS.
Il deploy su una piattaforma cloud (ad esempio AWS) e l’autenticazione tramite Oauth saranno considerati un plus.
