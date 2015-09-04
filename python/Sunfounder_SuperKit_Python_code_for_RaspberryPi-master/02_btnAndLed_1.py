import RPi.GPIO as GPIO
from time import sleep     # this lets us have a time delay (see line 12)

GPIO.setmode(GPIO.BCM)     # set up BCM GPIO numbering
GPIO.setup(18, GPIO.IN)    # set GPIO25 as input (button)
GPIO.setup(17, GPIO.OUT)   # Set LedPin's mode is output
GPIO.output(17, GPIO.HIGH) # Set LedPin high(+3.3V) to off led

# Define a threaded callback function to run in another thread when events are detected
def my_callback(channel):
    if GPIO.input(18):     # if port 11 == 1
        print "LIGHT ON"
	GPIO.output(17, GPIO.LOW) # Set LedPin high(+3.3V) to off led
    else:  
	print "LIGHT OFF"                # if port 11 != 1
	GPIO.output(17, GPIO.HIGH) # Set LedPin high(+3.3V) to off led

# when a changing edge is detected on port 25, regardless of whatever
# else is happening in the program, the function my_callback will be run
GPIO.add_event_detect(18, GPIO.FALLING, callback=my_callback)

raw_input("Press Enter when ready\n>")

try:
    print "When pressed, you'll see: Rising Edge detected on 25"
    print "When released, you'll see: Falling Edge detected on 25"
    sleep(30)         # wait 30 seconds
    print "Time's up. Finished!"

finally:                   # this block will run no matter how the try block exits
    GPIO.cleanup()         #

