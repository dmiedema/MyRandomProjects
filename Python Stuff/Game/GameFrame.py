from sys import exit
from random import randint
import Functions         # All functions needed are here... Hopefully
import time              # So there can be a pause... I like pauses.
import EnteredRoom       # Seperate functions for when a room is entered, in hopes to clean up the code of THIS
import PrettyPicture     # For the graphics that SETH was complaining about

#  Set up main menu
#  23 lines in standard terminal window 



class Game(object):


    def __init__(self, start):
        self.dead = [
            "You're dead.  Great job.",
            "You got owned!",
            "And... You still suck.",
            "Don't you wish you weren't so terrible?",
            "Way to suck."
        ]

        self.start = start


    def running(self):
        next = self.start

        
        
        while True:
            loc = getattr(self, next)
            next = loc()



    def death(self):
        display1 = '\n' * 28 
        display2 = self.dead[randint(0, len(self.dead) - 1)] 
        display3 = '\n' * 10
        print display1, display2, display3
        print display1, display2, display3
        print display1, display2, display3
        print display1, display2, display3
        print display1, display2, display3
        print display1, display2, display3
        print display1, display2, display3
        print display1, display2, display3
        print display1, display2, display3
        print display1, display2, display3
        print display1, display2, display3
        print display1, display2, display3
        print display1, display2, display3
        print display1, display2, display3
        print display1, display2, display3
        print display1, display2, display3
        print display1, display2, display3
        print display1, display2, display3
        print display1, display2, display3
        print display1, display2, display3
        print display1, display2, display3
        print display1, display2, display3
        print display1, display2, display3
        print display1, display2, display3
        time.sleep(5)
        return 'StartMenu'


    def StartMenu(self):
        print "\n" * 23, "\t" * 1, "   TTTTTT   H    H   EEEEE    GGGGG    AAA    M       M   EEEEE"
        print "\t" * 1, "     TT     H    H   EE      G        A   A   MM     MM   EE"
        print "\t" * 1, "     TT     HHHHHH   EEEE    G   GG   AAAAA   M M   M M   EEEE"
        print "\t" * 1, "     TT     H    H   EE      G    G   A   A   M  M M  M   EE"
        print "\t" * 1, "     TT     H    H   EEEEE    GGGGG   A   A   M   M   M   EEEEE"
        print "\n" * 2, "\t" * 2, "     Please Choose A Selection From Below."
        print "\n" * 2, "\t" * 3, "1  -  Begin Game"
        print "\n", "\t" * 3, "2  -  Statistics (Coming Soon)"
        print "\n", "\t" * 3, "3  -  Exit"
        print "\n" * 4
        choice = raw_input("Selection: ")

        if choice == "1":
            print "\n" * 20, '\t' * 2, "Alright then.  Let's get this shit started.", '\n' * 10
            print "\n" * 20, '\t' * 2, "Alright then.  Let's get this shit started.", '\n' * 10
            print "\n" * 20, '\t' * 2, "Alright then.  Let's get this shit started.", '\n' * 10
            print "\n" * 20, '\t' * 2, "Alright then.  Let's get this shit started.", '\n' * 10
            print "\n" * 20, '\t' * 2, "Alright then.  Let's get this shit started.", '\n' * 10
            print "\n" * 20, '\t' * 2, "Alright then.  Let's get this shit started.", '\n' * 10
            print "\n" * 20, '\t' * 2, "Alright then.  Let's get this shit started.", '\n' * 10
            print "\n" * 20, '\t' * 2, "Alright then.  Let's get this shit started.", '\n' * 10
            print "\n" * 20, '\t' * 2, "Alright then.  Let's get this shit started.", '\n' * 10
            time.sleep(1)
            return 'play'
            

        elif choice == "2":
            print "\n" * 20, "So... This part is still Under Construction.  Sorry, some day I'll implement.", "\n" * 10
            print "\n" * 20, "So... This part is still Under Construction.  Sorry, some day I'll implement.", "\n" * 10
            print "\n" * 20, "So... This part is still Under Construction.  Sorry, some day I'll implement.", "\n" * 10
            print "\n" * 20, "So... This part is still Under Construction.  Sorry, some day I'll implement.", "\n" * 10
            print "\n" * 20, "So... This part is still Under Construction.  Sorry, some day I'll implement.", "\n" * 10
            print "\n" * 20, "So... This part is still Under Construction.  Sorry, some day I'll implement.", "\n" * 10
            print "\n" * 20, "So... This part is still Under Construction.  Sorry, some day I'll implement.", "\n" * 10
            print "\n" * 20, "So... This part is still Under Construction.  Sorry, some day I'll implement.", "\n" * 10
            print "\n" * 20, "So... This part is still Under Construction.  Sorry, some day I'll implement.", "\n" * 10
            print "\n" * 20, "So... This part is still Under Construction.  Sorry, some day I'll implement.", "\n" * 10
            print "\n" * 20, "So... This part is still Under Construction.  Sorry, some day I'll implement.", "\n" * 10
            print "\n" * 20, "So... This part is still Under Construction.  Sorry, some day I'll implement.", "\n" * 10
            print "\n" * 20, "So... This part is still Under Construction.  Sorry, some day I'll implement.", "\n" * 10
            print "\n" * 20, "So... This part is still Under Construction.  Sorry, some day I'll implement.", "\n" * 10
            print "\n" * 20, "So... This part is still Under Construction.  Sorry, some day I'll implement.", "\n" * 10
            print "\n" * 20, "So... This part is still Under Construction.  Sorry, some day I'll implement.", "\n" * 10
            print "\n" * 20, "So... This part is still Under Construction.  Sorry, some day I'll implement.", "\n" * 10
            time.sleep(2.5)
            return 'StartMenu'

        elif choice == "3":
            print "\n" * 20, "\t" * 3, "     Thanks for Playing.", "\n" * 10
            exit(1)

        else:
            return 'StartMenu'
            
    def play(self):

        playing        = True
#        HP           = Functions.StartHP()
#        Ammo         = Functions.StartAmmo()
        CurrentFloor   = 1
#        Med          = Functions.StartMed()  
        RoomsFL        = Functions.RoomsOnFloor()
        RoomCount      = 0
        Stats          = [100, 14, 0]
        AlreadyEntered = []

        while playing == True:
            
            if Stats[0] < 1:
                playing = False
                return 'death'

            else:
                Rooms = RoomsFL[CurrentFloor]
                RoomEntered = Rooms
                cleardis = '\n' * 28
                underdis = '\n' * 4
                hud = "HP : %d \nAmmo : %d \nMedKits : %d \nCurrent Floor : %d \nRooms on Floor : %d" % (Stats[0], Stats[1], Stats[2], CurrentFloor, Rooms)
                print cleardis, hud, underdis
                print "\n\t To Enter a Room, enter the Room Number."
                print "\t There are %d rooms on this floor." % Rooms
                print "\n\t You have already entered Rooms : ", AlreadyEntered
                print '\n\t To heal, Enter "H"'
                print '\n\t To go down a level, Enter "S"\n\n\n'


                inpt = raw_input("Selection: ")

                # Tests to see if input is an integer
                intinpt = Functions.attempt_convert(inpt)

                if intinpt == None:

                    if inpt == "S" or inpt == "s":
                        if CurrentFloor == 11:
                            print "\nThere aren't any stairs to go down.  This is the Final Floor."
                            CurrentFloor = CurrentFloor
                        elif RoomCount < RoomEntered:
                            print "\nYou haven't entered all the rooms yet."
                            print "Once you enter all the rooms you can go down the stairs."
                        else:    
                            print '\ndown the stairs we go'
                            CurrentFloor = CurrentFloor + 1
                            RoomCount = 0
                            AlreadyEntered = []
                            PrettyPicture.DownStairs()
                            '''empty list of rooms entered.'''
                        time.sleep(2.5)

                    elif inpt == "H" or inpt == "h":
                        if Stats[2] > 0:
                            print '\nhealing...'
                            Stats[0] = Functions.MedKitHeal(CurrentFloor, Stats[0])
			    Stats[2] = Stats[2] - 1
                        else:
                            print '\nNo Medkits available.'
                            '''return to current location'''
                        time.sleep(2.5)

                    elif inpt == "idontwantaguniwannabechucknorris":
                        Stats[1] = 0

                    elif inpt == "fucktheroomsiwannamove":
                        RoomCount = Rooms
                    
                    elif inpt == "q" or inpt == "Q":
                        print "\n" * 31, "\t" * 3, "     Thanks for Playing.", "\n" * 10
                        exit(1)

                    else:
                        ''' back to start again.'''
                        print '\n\t\t  Invaild Input'
                        time.sleep(1)

                else:
                    if int(inpt) > Rooms or int(inpt) < 1:
                        '''error back to start'''
                        print '\nInvalid Room Number'
                        time.sleep(2.5)

                    elif int(inpt) <= Rooms:
                        '''something, list of entered rooms make sure 
                        its not equal to something that hasnt been entered.
                        '''
    #                    print 'goldy locks. just right.'
                        test = int(inpt)
                        Entered = False
                        x = 0
                        RoomCount = RoomCount + 1
                        length = len(AlreadyEntered)
    
                        if length == 0:
                            Entered = False

                        else:
                            while x != length:
                                if test == AlreadyEntered[x]:
                                    x = x + 1
                                    Entered = True
                                else:
                                    Entered = False
                                    x = x + 1


                        if Entered == True:
                            print "\nYou have already Entered this room."
                            print "\nGo in a different room."
                            AlreadyEntered.pop(-1)
                        elif Entered == False:
                            Statsreturned = EnteredRoom.entered(CurrentFloor, Stats[0], Stats[1], Stats[2])
                            Stats[0] = Statsreturned[0]
                            Stats[1] = Statsreturned[1]
                            Stats[2] = Statsreturned[2]
                        else:
                            time.sleep(2.5)

                        AlreadyEntered.append(test)

                        time.sleep(1)

                    else:
                        print'nothing goes here'





        

    
a_game = Game("StartMenu")
a_game.running()

            

























