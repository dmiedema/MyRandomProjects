from random import randint
import math

#  randomint is random number used in each funtion, it's the same in every function just recycled for consistancy.
#  strbreak is string created and last digit broken off in each funtion.  Again for consistancy in functions


###################################################################

#                        Map Functions

###################################################################

def RoomsOnFloor():
    """
    Calculates number of rooms on the floor and creates 
    a list with the rooms in them
    """
    #  Sets up Rooms dict to correct size
    Rooms = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
    #  Loop variable initialization
    z = 1
    for x in range(1,11):
        y = randint(2,8)
        Rooms[z] = y
        z = z + 1
    return Rooms




def RoomVisited(Rooms):
    """
    Determines if the room has been entered, if it has been
    entered, all items and enemies are removed.
    """




def ItemInRoom():
    """
    Decides if an item will be found in a room.  
    If an item is
    to be found, this function will also decide what 
    item it's going to be.
    """
    randomint = randint(1,100)
    strbreak = str(randomint)
    strbreak = strbreak[-1]
#    print strbreak

    if strbreak == "0" or strbreak == "4" or strbreak == "7" or strbreak == "9":
        MedKitFind = True
        AmmoFind = False
        return MedKitFind, AmmoFind

    elif strbreak == "1" or strbreak == "3" or strbreak == "5" or strbreak == "8":
        MedKitFind = False
        AmmoFind = True
        return MedKitFind, AmmoFind

    else:
        MedKitFind = False
        AmmoFind = False
        return MedKitFind, AmmoFind



###################################################################

#                       Enemy Present

###################################################################

def EnemyPresent():
    """
    Decides if an enemy is present on each move.  
    Room, Hallway or Stairs.
    """
    randomint = randint(1,1000)
    strbreak = str(randomint)
    strbreak = strbreak[-1]
    if strbreak == "2" or or strbreak == "3" or strbreak == "6" or strbreak == "7" or strbreak == "9":
        EnemPres = True
        return EnemPres
    else:
        EnemPres = False
        return EnemPres

###################################################################

#                       Enemy Attack Functions

###################################################################


def EnemyHit():
    """
    Calculates if Enemy will hit Player or not.  Not
    dependant on floor or anything else.  No ammo count
    for enemies.
    """
    randomint = randint(1,1000)
    strbreak = str(randomint)
    strbreak = strbreak[-1]

    if strbreak == "4" or strbreak == "5" or strbreak == "7" or strbreak == "0":
        EnemyHit = True
        return EnemyHit

    else:
        EnemyHit = False
        return EnemyHit



def EnemyDamage(CurrentFloor):
    """
    Calculates Enemy Damage will do.  
    Somewhat random number based on current floor.
    """
    Damage = randint(4,7)
    AddDamage = CurrentFloor * 1.5
    AddDamage = math.ceil(AddDamage)
    Damage = Damage + AddDamage
    return int(Damage)


def Enemy_HP(CurrentFloor):
    """
    Calculates Enemy Hit Points.  Some what random number
    based on current floor.
    """
    EnemyHP = randint(10,25)
    AddHealth = CurrentFloor * 1.5
    AddHealth = math.ceil(AddHealth)
    EnemyHP = EnemyHP + AddHealth
    return int(EnemyHP)


###################################################################

#                       Player Attack Functions

###################################################################




def PlayerDamage(CurrentFloor):
    """
    Calculates how much damage player will do.  
    Not dependant
    on current floor or anything else
    """
    Damage = randint(7,15)
    AddDamage = CurrentFloor * 1.5
    AddDamage = math.ceil(AddDamage)
    Damage = Damage + AddDamage
    return int(Damage)




def PlayerHit():
    """
    Calculates if Player will hit or miss enemy.  Not 
    dependant on floor or anything else.
    If ammo is 0 (zero) than this function is ignored
    as well as player damage.
    To call function use Ammo, PlayerHit
    """
    randomint = randint(1,1000)
    div3f = randomint / 3.0
    div3i = randomint / 3
    if randomint <= 500:
        PlayHit = True
        return PlayHit
    elif div3f == div3i:
        PlayHit = True
        return PlayHit
    else:
        PlayHit = False
        return PlayHit

        

###################################################################

#                       Attack Sequence

###################################################################


#  not even sure if i need this.

#def Attack():
    """
    Automatically goes through necessary steps of attacking player and enemy.
    Hopefully will have some kind of pause or good lay out for design.
    """

  #  if 



###################################################################

#                       Inventory & Stat Functions

###################################################################


def MedKitHeal(CurrentFloor, HP):
    """
    Adds HP to player, max HP of 100.  Multiplier of 1.5 per floor decended.
    """
    AddHP = randint(15,20)
    AddHPCF = CurrentFloor * 1.5
    AddHPCF = math.ceil(AddHPCF)
    AddHP = AddHP + AddHPCF
    HP = HP + AddHP
    if HP > 100:
        while HP > 100:
            HP = HP - 1
        return int(HP)
    else:
        return int(HP)


def AmmoPU(Ammo):
    if Ammo <= 42:
        Ammo = Ammo + 4
        if Ammo > 42:
            while Ammo > 42:
               Ammo = Ammo - 1
            return Ammo
        else:
            return Ammo
    else:
        print "Ammo Full"

def MedAdd(Med):
    if Med <= 4:
        Med = Med + 1
        return Med
    else:
        print "MedKits Full"
        return Med



def attempt_convert(inpt):
    try:
        return int(inpt)
    except ValueError:
        return None    


