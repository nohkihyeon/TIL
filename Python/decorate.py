from abc import abstractmethod


class Beverage():
    def __init__(self, description="이름 없는 음료"):
        self.description = description

    def getDescription(self):
        return self.description

    @abstractmethod
    def cost(self):
        pass

class CondimentDecorator(Beverage):
    @abstractmethod
    def getDescription(self):
        pass

class DarkRoast(Beverage):
    def __init__(self):
        Beverage.__init__(self, "DarkRoast")
    def cost(self):
        return 2100


class Mocha(CondimentDecorator):
    def __init__(self, beverage):
        if not isinstance(beverage, Beverage):
            raise TypeError("must use Beverage")
        self.beverage = beverage
    def getDescription(self):
        return self.beverage.getDescription()+ ", Mocha"
    def cost(self):
        return self.beverage.cost()+200


beverage = DarkRoast()
beverage = Mocha(beverage)
beverage = Mocha(beverage)
print(beverage.getDescription())
print(beverage.cost())
