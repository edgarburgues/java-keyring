# java-keyring

## Introduction
This encryption program was developed as part of a class project at my school. The original goal was to create an encrypted messaging program, but it ended up becoming a Java program for symmetric and asymmetric encryption. The program follows the principles of UNIX, meaning it is modular and minimalist in its command-line interface.

## Requirements
To use this program, you need to have Java installed on your computer. Basic knowledge of programming and encryption is recommended.

## Instructions
1. Download and extract the program files to a folder.
2. Open the command prompt in the folder where the files were extracted.
3. Run the program by typing `java -jar Encrypter.jar` in the command prompt and pressing Enter.

## Using the Program
The program has several options for creating keys, encrypting, decrypting, and signing messages.

### "create" Option (create key)
To create a key, use the "create" option with the following parameters:
- `--type`: Type of key. "S" for symmetric key or "A" for asymmetric key.
- `--length`: Key length.
- `--algorithm`: Encryption algorithm used.

Example usage: `java -jar Encrypter.jar create --type S --length 256 --algorithm AES`

The generated keys will be saved in the "keys" folder.

### "encrypt" Option (encrypt message)
To encrypt a message, use the "encrypt" option with the following parameters:
- `--private`: Name of the file containing the private key (for asymmetric encryption).
- `--input`: Name of the file containing the message to encrypt.

Example usage: `java -jar Encrypter.jar encrypt --private keys/key.RSA.prv --input input/message.txt`

The encrypted message will be saved in the "input" folder with the extension ".enc".

### "decrypt" Option (decrypt message)
To decrypt a message, use the "decrypt" option with the following parameters:
- `--public`: Name of the file containing the public key (for asymmetric encryption).
- `--input`: Name of the file containing the encrypted message.

Example usage: `java -jar Encrypter.jar decrypt --public keys/key.RSA.pub --input input/message.enc`

The decrypted message will be saved in the "input" folder with the extension ".dec".

### "sign" Option (sign message)
To sign a message, use the "sign" option with the following parameter:
- `--input`: Name of the file containing the message to sign.

Example usage: `java -jar Encrypter.jar sign --input input/message.txt`

The signature will be saved in the "hash" folder with the extension ".hash".

### "view" Option (view key)
To view a key, use the "view" option with the following parameter:
- `--input`: Name of the file containing the key.

Example usage: `java -jar Encrypter.jar view --input keys/key.RSA.pub`
