README
n-Puzzle is een spel waarin een foto in tiles wordt opgedeeld. Deze tiles worden gemixt en de gebruiker dient de tiles weer op de goede volgorde te zetten.

Activities
==========================
Activity 1: Beginscherm
--------------------------
In dit scherm kan de gebruiker een afbeelding kiezen. De afbeeldingen worden met de app meegeleverd. De afbeeldingen worden weergegeven in een ListView(). Buiten het kiezen van een afbeelding heeft dit scherm geen functie.

Activity 2: Preview
--------------------------
In dit scherm wordt er een sneak-preview weergegeven van de afbeeldingen. Dit duurt slechts 3 seconden. Daarna eindigde de Activity en gaat de app verder naar Activity 3.

Activity 3: Game
--------------------------
In dit scherm wordt de afbeelding weergegeven en het daadwerkelijk spel gespeeld. In dit scherm kan men op de tiles klikken en de tiles worden verschoven met de empty tile (mits dit is toegestaan). In met menu zijn er een drietal opties:
+ Terug naar beginscherm
+ Niveau instellen
    - Hard
    - Normal
    - Easy
+ Herstart spel

Verder is er in het scherm een counter te zien die het aantal gezette stappen bijhoudt.

Activity 4: Won
--------------------------
De gebruiker heeft het spel gewonnen. Er wordt een bericht weergegeven om de gebruiker te feliciteren met zijn/haar overwinning.

Functionaliteiten
==========================

Algemene functionaliteiten
--------------------------
Switchen tussen schermen wordt afgehandeld door intent. Intent maakt het mogelijk om tussen de verschillende activities te wisselen.

De afbeeldingen weergeven in het beginscherm wordt afgehandeld door new ArrayAdapter(); getListView().setListAdapter(a). De afbeeldingen worden  in een lijst weergegeven waarna de gebruiker een keuze kan maken.

Er wordt een method aangemaakt OnItemClickListener. OnItemClickListener wordt gebruikt om een reactie te geven op de gebruiker wanneer deze ergens op klikt/drukt. Er wordt verwezen naar een actie die moet worden uitgevoerd. 

Afbeeldingen
--------------------------
Om een afbeelding in te laden wordt r.drawable(location) gebruikt. Deze syntax zorgt er voor dat de afbeelding (.bmp) in het geheugen wordt geladen. De afbeelding wordt nog niet daadwerkelijk weergegeven. De afbeelding die is gekozen door de gebruiker moet in het juiste formaat worden weergegeven. De afbeelding moet dus worden geschaald, oftewel gecropt. Bitmap.createScaledImage (Bitmap bitmap, int width, int height, boolean filter) wordt hiervoor gebruikt. Bitmap.createBitmap wordt gebruikt om een selectie te maken van de gecropt afbeelding. In createBitmap word een x-as en y-as waarde meegegeven. Deze waardes worden meegegeven om het beginpunt van de selectie aan te geven (standaard is dit 0,0). Het daadwerkelijk weergeven van de afbeelding wordt met ImageView() gedaan. 

De afbeeldingen moeten in tiles worden weergegeven. Dit kan door het gebruik maken van een GridView() of een tableLayout(). Om een tile te representeer wordt er een onderliggende nummering bijgehouden, net als bij de game fifteen. Als de nummers op volgorde liggen 1,2,3,.. dan is het spel gewonnen.

Werking spelelement
--------------------------
Het daadwerkelijk swappen van de tiles wordt gedaan met setImageBitmap() of setImageDrawable(). Hiermee worden de afbeeldingen vervangen of verwisseld. Dit is alleen toegestaan wanneer een afbeelding next to empty tile is.

Wanneer de gebruiker het spel afsluit / pauzeerd moet de huidige state van het bord opgeslagen worden. De volgende onderdelen moeten worden onthouden:
+ State van het bord
+ Hoeveelheid swaps
+ Moeilijkheidsgraad

Het saven wordt afgehandeld door super.onPause(); sharedPreferencesEditer.put<type>(string name, int value). Natuurlijk moet de state ook opgeroepen kunnen worden, dit gebeurd met super.onCreate() en super.onResume();getPreferences(int mode). 

In het speelscherm kan er een menu worden getoond. Elk menu heeft childs. Deze worden aangegeven met <item>. Een menu wordt gemaakt met onCreateOptionsMenu(menu menu). Bij de selectie van een menu-item wordt onOptionItemSelected(item) aangeroepen.
