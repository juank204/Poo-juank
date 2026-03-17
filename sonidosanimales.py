# Sonidos de 5 animales en Python

def sonido_animal(animal):
    sonidos = {
        "1": ("perro", "Guau guau!"),
        "2": ("gato",  "Miau miau!"),
        "3": ("vaca",  "Muuuuu!"),
        "4": ("rana",  "Croac croac!"),
        "5": ("leon",  "Roaaarrr!"),
    }
    if animal in sonidos:
        nombre, sonido = sonidos[animal]
        print(f"El {nombre} dice: {sonido}")
    else:
        print("Opcion no valida")

print("Selecciona un animal:")
print("1. Perro")
print("2. Gato")
print("3. Vaca")
print("4. Rana")
print("5. Leon")

opcion = input("Escribe el numero: ")
sonido_animal(opcion)