# Secure Message Encryptor

**Secure Message Encryptor** is a Java application that demonstrates basic text encryption using a graphical user interface (GUI). This project is ideal for learning how to work with Java Swing components and basic encryption techniques.

## Project Structure

The project is organized into several key components:

### 1. **`SecureMessageFrame.java`**
   - **Purpose**: This is the main class that creates and manages the GUI for the application.
   - **Key Components**:
     - **Menu Bar**: Provides options to open files, encrypt messages, save encrypted messages, clear text areas, and exit the application.
     - **Text Areas**: 
       - **Plain Message Area**: Displays the loaded plain text.
       - **Encrypted Message Area**: Shows the result of the encryption.
     - **Listeners**:
       - **`openFileLIstener`**: Handles file opening and loading the content into the plain message text area.
       - **`EncryptListener`**: Encrypts the plain text using the `MessageEncrypter` class and displays it in the encrypted message area.
       - **`SaveEncryptedMessageListener`**: Saves the encrypted message to a new file.
       - **`clearListener`**: Clears both text areas.
       - **`ExitListener`**: Exits the application.

### 2. **`Message.java`**
   - **Purpose**: Defines a simple model class for handling messages.
   - **Key Methods**:
     - `getMessageContents()`: Returns the contents of the message.
     - `setMessageContents(String messageContents)`: Sets the contents of the message.

### 3. **`MessageEncrypter.java`**
   - **Purpose**: Contains the encryption logic.
   - **Key Method**:
     - `encryptMessage(String message)`: Encrypts the input message by shifting character codes. This is a basic form of encryption where each character’s ASCII value is increased by 3.

### 4. **`AssigmentQu2Test.java`**
   - **Purpose**: The entry point of the application.
   - **Key Function**:
     - `main(String[] args)`: Initializes and runs the `SecureMessageFrame`.

## How to Run the Application

1. **Clone the Repository**:
    ```bash
    git clone https://github.com/yourusername/secure-message-encryptor.git
    ```

2. **Compile the Code**:
    ```bash
    javac -d bin src/**/*.java
    ```

3. **Run the Application**:
    ```bash
    java -cp bin assigmentqu2test.AssigmentQu2Test
    ```

## Features

- **Open**: Load plain text files.
- **Encrypt**: Transform the plain text into encrypted text.
- **Save**: Save the encrypted text to a file.
- **Clear**: Reset text areas.
- **Exit**: Close the application.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

