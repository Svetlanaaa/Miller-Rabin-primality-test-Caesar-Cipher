from GenerateBigPrime import *
from Person import Person

generator = Generator()
p = generator.generate_big_prime(17)
print("Prime = " + str(p))
g = generator.find_root(p)

Alice = Person(p, g, "Alice")
Bob = Person(p, g, "Bob")

Alice.calculate_X()
Bob.calculate_X()

Alice.write(Alice.X)
Bob.read()
Bob.write(Bob.X)
Alice.read()

Alice.calculate_K()
Bob.calculate_K()

print("Alice found secret key ", Alice.K)
print("Bob found secret key ", Bob.K)