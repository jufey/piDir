__author__ = 'jufey'
import homeElements


class NewRoom:
    def __init__(self, name, pl):
        self.name = name
        self.elements = []
        self.pinList = pl

    # Add a new openable thing to the room
    def add_open(self, n, typeof, pin):
        n = homeElements.NewOpenable(n, self.name, typeof, pin)
        self.elements.append(n)
        return

    # Add a new device to the room
    def add_device(self, n, typeof, pin):
        n = homeElements.NewDevice(n, self.name, typeof, pin)
        self.elements.append(n)
        return

    def print_overview(self):
        for el in self.elements:
            el.print_status()

    def get_element(self, name):
        for e in self.elements:
            if e.name == name:
                return e

    def update_all_elements(self):
        for e in self.elements:
            e.update_status(self.pinList[e.get_pin()])
