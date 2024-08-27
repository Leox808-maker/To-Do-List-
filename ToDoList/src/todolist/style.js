.base {
    -fx-background-color: #e3dfdc;
    -fx-padding: 50px 0 50px;
    -fx-font-size: 15;
    -fx-font-family: Arial;
}

#titolo {
    -fx-alignment: center;
    -fx-text-alignment: center;
    -fx-font-size: 27;
    -fx-padding: 0;
}

.barrato .text {
    -fx-strikethrough: true;
}

.pulsante .text {
    -fx-insets: 0;
}

.pulsanteElimina {
    -fx-background-image: url("Assets/Images/delete.jpg");
    -fx-background-size: 100% 100%;
    -fx-background-position: center;
    -fx-max-height: 25px;
    -fx-min-width: 25px;
}

.pulsanteCompletata {
    -fx-background-image: url("Assets/Images/checkBtn.jpg");
    -fx-background-size: 100% 100%;
    -fx-background-position: center;
    -fx-min-height: 27px;
    -fx-min-width: 25px;
    -fx-border-radius: 4px;
    -fx-padding-right: 3px;
}

.nonCompletata {
    -fx-background-image: url("Assets/Images/xBtn.png");
    -fx-background-size: 100% 100%;
    -fx-background-position: center;
    -fx-min-height: 25px;
    -fx-min-width: 25px;
}

#pulsanteAggiungi {
    -fx-background-image: url("Assets/Images/plusSign.jpg");
    -fx-background-size: 100% 100%;
    -fx-background-position: center;
    -fx-min-width: 70px;
    -fx-min-height: 45px;
    -fx-border-radius: 10px;
}

#pulsanteAggiungi:pressed {
    -fx-min-width: 65px;
    -fx-min-height: 40px;
    -fx-padding: 5px;
}

#pulsanteSalva {
    -fx-background-image: url("Assets/Images/save.jpg");
    -fx-background-size: 100% 100%;
    -fx-background-position: center;
    -fx-min-width: 70px;
    -fx-min-height: 45px;
}
#pulsanteSalva:pressed {
    -fx-min-width: 65px;
    -fx-min-height: 40px;
    -fx-padding: 5px;
}

#campoInserimento {
    -fx-min-height: 44px;
    -fx-font-size: 25;
}

.bottoneFiltro {
    -fx-padding: 10px;
}