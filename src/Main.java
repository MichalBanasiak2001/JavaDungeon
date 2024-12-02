//Zaimportowanie klasy Random, która jest wykorzystywane do losowania ID przeciwnika.
import java.util.Random;
//Zaimportowanie klasy Scanner, która jest wykorzystywana do odczytywania danych z konsoli.
import java.util.Scanner;

//Klasa główna
public class Main {
    ////////////////
    //Zmienne gry
    ////////////////
    private static int current_Level = 0; //Zienna aktualnego poziomu lochu.
    private static Scanner scannerWaitForEnter = new Scanner(System.in); //Zmienna  klasy Scanner do oczekiwanie na wcisniecie enter.
    
    ////////////////
    //Zmienne gracza
    ////////////////
    private static int player_class = 1; //Zmienna aktualnej klasy gracza
    private static String player_class_name; //Zmienna aktualnej nazwy klasy gracza
    private static int player_health = 100;  //Zmienna aktualnego zycia gracza
    private static int player_damage = 25; //Zmienna aktualnych obrażeń gracza
    private static int player_hp_potion_count = 0; //Zmienna ilości mikstur leczniczych
    
    ////////////////
    //Zmienne aktualnego przeciwnika
    ////////////////
    private static int      current_Enemy_ID; // ID przeciwnika potrzebne do losowania przeciwnika
    private static String   current_Enemy_name; // Nazwa aktualnego przeciwnika
    private static int      current_Enemy_health; // Życie aktualnego przeciwnika
    private static int      current_Enemy_damage; // Obrazenia aktualnego przeciwnika

    
    ////////////////
    //Metoda do losowania ID przeciwnika
    ////////////////
    public static void enemyRandomizer() 
    {
        Random rand = new Random();
        int randomNumber = rand.nextInt(100); // Generuje numer od 0 do 99.
        current_Enemy_ID = randomNumber;
    }

    ////////////////
    //Główna metoda
    ////////////////
    public static void main(String[] args) 
    {
        openingScreen();
    }
    
    ////////////////
    //Metoda ekranu otwarcia gry
    ////////////////
    public static void openingScreen()
    {
        System.out.println("Gra autostwa Michała Banasiaka 2024 r.");
        System.out.println("Witaj w Lochach lorda Jawy!");
        System.out.println();
        System.out.println("Wybierz klase gracza: 1. Wojownik, 2. Zwiadowca, 3. Czarodziej.");
        System.out.println("(Wybierz klase gracza wpisując odpowiedni numer w konsoli).");
        System.out.println();
        PlayerClassSelect();
    }
    ////////////////
    //Metoda wyboru klasy gracza
    ////////////////
    public static void PlayerClassSelect()
    {
        Scanner scanner = new Scanner(System.in); //Zmienna klasy Scanner
        String player_choice = scanner.nextLine(); //Zmienna wyboru klasy gracza

        //Warunki wyboru klasy
        if (player_choice.equals("1")) {
            player_class = 1;
            player_class_name = "Wojownik";
            player_health = 100;
            player_damage = 25;
        }
        else if (player_choice.equals("2")) {
            player_class = 2;
            player_class_name = "Zwiadowca";
            player_health = 75;
            player_damage = 15;
        }
        else if (player_choice.equals("3")) {
            player_class = 3;
            player_class_name = "Czarodziej";
            player_health = 50;
            player_damage = 30;
        }
        //Tryb Godmode do testów
        else if (player_choice.equals("Godmode")) {
            player_class = 4;
            player_class_name = "Godmode";
            player_health = 100000;
            player_damage = 100;
        }
        //Zabezpieczenie w przypadku błędnych wyborów
        else
        {
            System.out.println("Nie ma takiej opcji. Wybierz ponownie.");
            scanner = new Scanner(System.in);
            player_choice = scanner.nextLine();
            PlayerClassSelect();
        }

        System.out.println("Wybrałeś klase: " + player_class_name);
        System.out.println("Naciśnij ENTER aby kontynuować...");
        scannerWaitForEnter.nextLine();
        preBattleScreen();
    }

    ////////////////
    //Ekran przed walką
    ////////////////
    public static void preBattleScreen()
    {
            
            enemyRandomizer(); //Odwołanie do metody losowania ID przeciwnika

            //Warunki które ustawiają statystyki przeciwnika w zależności od jego ID
            
            //Statystyki Wielkiego Pająka
            if (current_Enemy_ID < 50 && current_Level < 2)
            {
                current_Enemy_name = "Wielki pająk";
                current_Enemy_health = 50;
                current_Enemy_damage = 10;
            }
            //Statystyki Szkieleta Zwiadowcy
            else if (current_Enemy_ID < 80 && current_Level < 4)
            {
                current_Enemy_name = "Szkielet Zwiadowca";
                current_Enemy_health = 100;
                current_Enemy_damage = 20;
            }
            //Statystyki Szkieleta Wojownika
            else if (current_Enemy_ID < 70 && current_Level < 4)
            {
                current_Enemy_name = "Szkielet Wojownik";
                current_Enemy_health = 100;
                current_Enemy_damage = 20;
            }
            //Statystyki Szkieleta Łucznika
            else if (current_Enemy_ID < 90 && current_Level < 4)
            {
                current_Enemy_name = "Szkielet Łucznik";
                current_Enemy_health = 100;
                current_Enemy_damage = 20;
            }
            //Statystyki Lorda Jawy
            else if (current_Enemy_ID <= 100 && current_Level == 4)
            {
                current_Enemy_name = "Lord Jawa";
                current_Enemy_health = 200;
                current_Enemy_damage = 40;
            }

            //Przygotowanie do walki
            System.out.println();
            System.out.println("Wchodzisz do nowego pomieszczenia w lochach. Poziom: " + current_Level);
            System.out.println("Zostajesz zaatakowy przez " + current_Enemy_name + "!");
            System.out.println();
            System.out.println("Naciśnij ENTER aby kontynuować...");
            scannerWaitForEnter.nextLine();

            //Przejście do metody "battlescreen", która odpowiada za walkę.
            battlescreen();
        
    } 

    ////////////////
    //Metoda ekranu walki
    ////////////////
    public static void battlescreen() 
    {   
        //Zakończenie gry, jeśli graczowi uda się pokonać Lorda Jawę (ostatniego przeciwnika).
        if (current_Enemy_health <= 0 && current_Level == 4)
        {
            victory();
        }
        //Główny ekran walki z warunkiem, gdy przeciwnik dalej żyje.
        else if (current_Enemy_health > 0)
        {
            System.out.println();
            System.out.println("Przeciwnik atakuje! ");//+ current_Enemy_ID
            //player_health = player_health - current_Enemy_damage;
            System.out.println();
            System.out.println("Przeciwnik: " + current_Enemy_name);
            System.out.println("Życie: " + current_Enemy_health);
            System.out.println();
            System.out.println("Gracz: " + player_class_name);
            System.out.println("Życie: " + player_health);
            System.out.println();
            System.out.println("Twój ruch: 1. Atak, 2. Użyj mikstury leczniczej (Ilość: " + player_hp_potion_count + ").");
            System.out.println();
            battleLogic();
        }
        //Ekran po pokonaniu przeciwnika.
        else
        {

            System.out.println();
            System.out.println("Udało ci się pokonać " + current_Enemy_name + "!");
            
            //Losowanie czy gracz zdobędzie miksture leczniczą po pokonaniu przeciwnika.
            Random rand = new Random(); //Instancja klasy Random
            int randomNumber = rand.nextInt(100); // Generuje numer od 0 do 99.
            if (randomNumber < 50) //Warunek ustawiający prawdopodobność zdobycia mikstury leczniczej
            {
                player_hp_potion_count = player_hp_potion_count + 1;
                System.out.println("Zdobywasz miksture leczniczą!");    
            }
            //Zwiększenie poziomu lochu o jeden.
            current_Level++;
            //Przygotowanie do nowej walki
            preBattleScreen();
        }    

    }
    ////////////////
    /// Metoda "logiki" walki.
    ////////////////
    public static void battleLogic()
    {
        
        Scanner scanner = new Scanner(System.in); //Zmienna klasy Scanner
        String player_choice = scanner.nextLine(); //Zmienna wyboru gracza. Gracz możesz wybrać dwie opcje: 1. Atak, 2. Użyj mikstury leczniczej.
    
        // Wybór gracza numer 1 - zatakowanie przeciwnika
        if (player_choice.equals("1")) 
        {
            current_Enemy_health = current_Enemy_health - player_damage;
            battlescreen();
        }
        // Wybór gracza numer 2 - używanie mikstury leczniczej
        else if (player_choice.equals("2")) 
        {
            //Jeśli gracz posiada miksture leczniczą, to jej wykorzystuje.
            if (player_hp_potion_count > 0)
            {
                System.out.println("Używasz mikstury leczniczej, która odnawia 50 punktów życia. ");
                player_health = player_health + 50; //Zwiekszenie zycia gracza o 50 pkt
                player_hp_potion_count--; //Zmniejszenie ilosci mikstur leczniczych
                System.out.println("Naciśnij ENTER aby kontynuować...");
                scannerWaitForEnter.nextLine();
                battlescreen();
            }
            //Jeśli gracz nie posiada mikstur leczniczych, zostaje powiadomiony że nie ma takiego przedmiotu.
            else
            {
                System.out.println("Niestety nie masz żadnej mikstury.");
                System.out.println();
                System.out.println("Naciśnij ENTER aby kontynuować...");
                scannerWaitForEnter.nextLine();
                battlescreen();
            }

        }
        //Zabezpiecznie przed wybraniem nieistniejącej opcji.
        else
        {
            System.out.println("Nie ma takiej opcji. Wybierz ponownie.");
            scanner = new Scanner(System.in);
            player_choice = scanner.nextLine();
            battleLogic();
        }    
    }



    //////////////////////
    //Metoda przegranej gry (kiedy gracz straci wszystkie punkty życia)
    //////////////////////
    public static void gameover()
    {
        System.out.println("Niestety nie podołałeś się Lochu Jawy. Co skończyło się twoją śmiercią!");
    }
    
    //////////////////////
    //Metoda zwycięstwa po pokonaniu głównego przeciwnika
    //////////////////////
    public static void victory()
    {
        System.out.println();
        System.out.println("Udało ci się pokonać Lorda Jawę!");
        System.out.println("Czujesz się wykończony swoją przygodą. Postanawiasz zebrać wszystkie skarby jakie znajdowały się w pokoju Lorda Jawy.");
        System.out.println("Teraz jedynie musisz wydostać z lochu, ale to raczej nie będzie trudne.");
        System.out.println();
        System.out.println("Naciśnij ENTER aby kontynuować...");
        scannerWaitForEnter.nextLine();
        
        System.out.println("Gratuluję udało ci się przejść Loch Lorda Jawy!");
        System.out.println("Dziękuję za grę!");  
    }

}
    
