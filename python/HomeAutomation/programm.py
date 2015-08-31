__author__ = 'Justin Marks'
import Room


# Demo Pins
pinListBedroom = {
    "pin1": True,
    "pin2": True,
    "pin3": True,
    "pin4": True,
    "pin5": True,
}

bedroom = Room.NewRoom("bedroom", pinListBedroom)
# kitchen = Room.NewRoom("kitchen")

bedroom.add_device("tv", "device", "pin1")
bedroom.add_device("fan", "device", "pin2")
bedroom.add_device("overhead_light", "light", "pin3")
bedroom.add_device("table_light", "light", "pin4")
bedroom.add_open("door", "door", "pin5")

# Demo Part
bedroom.update_all_elements()
pinListBedroom["pin1"] = False
bedroom.update_all_elements()
