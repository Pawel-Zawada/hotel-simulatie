package pathFinding;

public class PathFinding {
    /**
     * List open nodes  // deze bevat de nodes die nog berekend moeten worden
     * List closed nodes   // deze bevat de nodes die al berekend zijn, hier gaan we niet meer naar terug
     *
     *  Stap 1 voeg de start node toe aan de open list
     *
     *  Loop
     *      Current node = node in open list met de laagste f cost
     *      verwijder current van open list
     *      voeg current node toe aan closed list
     *
     *      Als de current node de target node is, return, want we hebben het pad
     *
     *      Anders
     *      Voor elke naaste node van de current node:
     *          Als de node niet bewandelbaar is OF als de node in de clossed list zit
     *              Ga naar de volgende node
     *          Als het nieuwe pad naar de naaste node korter is OF als de node nog niet in de open list zit
     *              Set f cost van de naaste node
     *              Set parent van naaste node naar de huidige node
     *              Als de naaste node niet in de open list staat voeg hem dan toe
     *
     *
     *  1 Maak een grid node class, kan bewandel baar zijn of solid
     *  2 Maak een grid en vul die met nodes
     *  3
     */




}
