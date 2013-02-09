'''       Entered Room       '''

import Functions
import time
import PrettyPicture

def entered(CurrentFloor, HP, Ammo, Med):
    print '\n' * 31
    EnemyHP = Functions.Enemy_HP(CurrentFloor)
    EnemyPresent = Functions.EnemyPresent()
    living = True

    if EnemyPresent == True:
        print '\n'*10, "There's an Enemy in the room!"
        print "\nHe's got ", EnemyHP, " Hit Points!"

        time.sleep(1)
        while living == True:
            if Ammo > 0:
                PlayerHit = Functions.PlayerHit()


                if PlayerHit == True:
                    PlayerDamage = Functions.PlayerDamage(CurrentFloor)
                    EnemyHP = EnemyHP - PlayerDamage

                    if EnemyHP <= 0:
                        living = False
                        print "\nNice job! He's dead!"
                        time.sleep(1)
                        PrettyPicture.PAHit()
                    else:
                        Ammo = Ammo - 1
                        print "\nYou hit him for ", PlayerDamage, " damage!"
                        time.sleep(1)
                        print "\nHis Health is now at ", EnemyHP
                        time.sleep(1)
                        print "\nYou have ", Ammo, " shots left."
                        time.sleep(1)
                        PrettyPicture.PAHit()

                else:
                    print "\nYou missed!"
                    time.sleep(1)
                    Ammo = Ammo - 1
                    print "\nYou have ", Ammo, " shots left."
                    time.sleep(1)
                    PrettyPicture.PAMiss()

            else:

                PlayerDamage = 6 + CurrentFloor
                EnemyHP = EnemyHP - PlayerDamage

                if EnemyHP <= 0:
                    living = False
                else:
                    living = True

                print "\nYou have no Ammo!"
                time.sleep(1)
                print "\nYou do ", PlayerDamage, ", the Enemy's Health is ", EnemyHP
                time.sleep(1)
                PrettyPicture.NAattack()

            EnemyHit = Functions.EnemyHit()
            if EnemyHP <= 0:
                print '\n'
            else:

                if EnemyHit == True:
                    EnemyDamage = Functions.EnemyDamage(CurrentFloor)
                    HP = HP - EnemyDamage

                    if HP <= 0:
                        print "You died."
                        exit(1)
                    else:
                        print "\nYou got hit for ", EnemyDamage, "!"
                        time.sleep(1)
                        print "\nYou're now at ", HP, " Hit Points!"
                        time.sleep(1)
                        PrettyPicture.EAHit()


                else:
                    print "\nHe missed!"
                    time.sleep(1)
                    PrettyPicture.EAMiss()
                    


    else:
        print "\nThere's no Enemy here."
        time.sleep(1)
        pass

    Item = Functions.ItemInRoom()
    if Item == (True, False):
        print "There's a Medkit In the Room."
        time.sleep(1)
        PrettyPicture.Med()
        Med = Functions.MedAdd(Med)
        time.sleep(1)
    elif Item == (False, True):
        print "There's Ammo in the Room."
        time.sleep(1)
        PrettyPicture.Ammo()
        Ammo = Functions.AmmoPU(Ammo)
        time.sleep(1)
    else:
        print "\nNo Items in this Room."
        time.sleep(1)

    return HP, Ammo, Med
