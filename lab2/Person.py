import random


class Person:

    def __init__(self, p, g, _name):
        self.p = p
        self.g = g
        self.X = None
        self.Y = None
        self.K = None
        self.name = _name

    def write(self, num):
        with open('dialog.txt', 'w', encoding='utf-8') as f:
            print('{} sent {}'.format(self.name, num))
            f.write(str(num))

    def read(self):
        with open('dialog.txt', 'r', encoding='utf-8') as f:
            self.temp = int(f.read())
            self.Y = self.g ** self.temp % self.p
            print('{} got {}'.format(self.name, self.temp))

    def calculate_X(self):
        self.X = random.randint(1, self.p - 1)

    def calculate_K(self):
        self.K = self.Y ** self.X % self.p