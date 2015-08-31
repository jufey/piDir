__author__ = 'Justin Marks'
import time


class HomeElements:
    def __init__(self, name, room, typeof, pin):
        self.name = name
        self.room = room
        self.status = False
        self.type = typeof
        self.pin = pin

    def __str__(self):
        return self.name

    # Returns the name
    def get_name(self):
        return self.name

    # Returns the room
    def get_room(self):
        return self.room

    # Returns the current status
    def get_status(self):
        return self.status

    # Returns the used GPIO pin
    def get_pin(self):
        return self.pin

    # Returns the type
    def get_type(self):
        return self.type


# SubClass from homeElements
# Handles all openable Parts of the homeElements
class NewOpenable(HomeElements):
    def __init__(self, name, room, typeof, pin):
        super().__init__(name, room, typeof, pin)

    # Print the object
    def print_status(self):
        s = "{}: \"{}\" in room \"{}\" is \"{}\"\n".format(time.asctime(), self.name, self.room,
                                                         "opened" if True == self.status else "closed")
        print(s)
        log_file = open("log.txt", "a")
        log_file.write(s)
        log_file.close()

    def update_status(self, new_status):
        if self.get_status() == new_status:
            return True
        else:
            self.status = new_status
            self.print_status()
            return False


# SubClass from HomeElements
# Handles all devices of the HomeElements
class NewDevice(HomeElements):
    def __init__(self, name, room, typeof, pin):
        super().__init__(name, room, typeof, pin)

    # Print the object
    def print_status(self):
        s = "{}: \"{}\" in room \"{}\" is \"{}\"\n".format(time.asctime(), self.name, self.room,
                                                           "turned on" if True == self.status else "turned off")
        print(s)
        log_file = open("log.txt", "a")
        log_file.write(s)
        log_file.close()

    def update_status(self, new_status):
        if self.get_status() == new_status:
            return True
        else:
            self.status = new_status
            self.print_status()
            return False
