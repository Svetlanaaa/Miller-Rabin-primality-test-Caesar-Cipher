from random import SystemRandom


class MillerRabinTest:

    def __init__(self, p):
        self.p = p
        self.m = None
        self.b = 0

    def calculate_b(self):
        _p = self.p -1
        while _p % 2 == 0:
            _p = _p / 2
            self.b = self.b+1

    def calculate_m(self):
        _p = self.p - 1
        self.m = _p // (2**self.b)

    def generate_a(self):
        cryptogen = SystemRandom()
        return cryptogen.randrange(2, self.p - 2)

    def is_prime(self, iterations):
        self.calculate_b()
        self.calculate_m()

        for i in range(0, iterations):
            a = self.generate_a()
            z = a ** self.m % self.p
            if z == 1 or z == self.p - 1:
                continue
            for k in range(0, self.b - 1):
                z = (z * z) % self.p
                if z == 1:
                    return False
                if z == self.p - 1:
                    break
                return False
        return True