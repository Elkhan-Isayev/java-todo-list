# Welcome
It's programm that help you plan your affairs.
## Installation
1. Download project to your computer
2. Open project in the terminal 
3. Run docker-compose up -d
   
4. Download javafx-sdk from [Gluonhq](https://gluonhq.com/products/javafx/)
   
5. For IntelliJ IDEA, open
   ```console
   File->Project Structure->Project Settings->Modules->Add
   ```
   Add all ```jar``` files inside ```lib``` folder as a module.
   
6. For IntelliJ IDEA, open
   ```console
   Run->Edit Configurations->Main->Modify options->Add VM option
   ```
   Then paste next option
    ```console
     --module-path "path to downloaded sdk"\lib --add-modules=javafx.controls,javafx.fxml
    ```
7. Run Main.java file

## Can I contribute?

Yes you can!  Contributions are welcomed. Thank you for considering contributing to the Todo List!
