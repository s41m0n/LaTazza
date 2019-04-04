# ASSUMPTIONS
1. il sistema LaTazza gira come desktop application con due interfacce distinte:
	- interfaccia admin per il manager
	- interfaccia utente per l'employee
	- il visitor non può usare l'applicazione desktop, ma può solo rivolgersi al manager	
2. Entrambe le interfacce comunicano con un server centrale
	- il server memorizza i dati sugli account, ordini, capsule disponibili e pagamenti
3. un employee che ha scaricato l'applicazione desktop può creare un account proprio
4. è possibile acquistare un tot di cialde alla volta di diversi tipi dall'applicazione desktop (employee)	
5. l'interfaccia della desktop application privata per l'employee permette:
	- creare un account
	- login con il proprio account
 	- ordinare cialde (massimo 100 cialde, anche di tipi diversi)
	- aggiungere un metodo di pagamento all'account
	- aggiungere denaro al proprio account per comprare crediti
	- saldare il debito del proprio account
	- piazzare ordini per il futuro (cialde non in magazzino)
6. l'interfaccia della desktop application privata (per il manager) permette di:
	- login con il proprio account
	- gestione avanzata delle opzioni e dei permessi per il proprio account
	- gestione dello stato degli altri account (credito e debito, ordini pendenti)
	- gestione della desktop application pubblica 
	- gestione inventario
	- acquisto di scatole di capsule
	- pagamento degli ordini di cialde	
7. LaTazza può vendere capsule di diversi venditori
	- ogni venditore che vuole supportare l'applicazione deve mettere a disposizione delle API con cui LaTazza si interfaccia
8. Il manager effettua l'ordine delle cialde a nome suo, pagando con il proprio sistema bancario
	- da parte di LaTazza supporto alle API del sistema bancario per l'interfacciamento

