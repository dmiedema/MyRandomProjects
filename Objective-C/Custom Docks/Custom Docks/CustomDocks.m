//
//  CustomDocks.m
//  Custom Docks
//
//  Created by Daniel Miedema on 1/17/12.
//  Copyright (c) 2012 ThisIsAnApp. All rights reserved.
//



#import "CustomDocks.h"



@implementation CustomDocks

- (id) init {
    [super init];
    dockFolderNames = [[NSMutableArray alloc] init];
    return self;
}


-(void) awakeFromNib {
    // Set up MenuBar Item
    statusItem = [[[NSStatusBar systemStatusBar] statusItemWithLength:NSVariableStatusItemLength] retain];
    
    NSBundle *bundle = [NSBundle mainBundle];
    
    [statusItem setHighlightMode:YES];
    
    statusImage = [[NSImage alloc] initWithContentsOfFile:[bundle pathForResource:@"icon" ofType:@"png"]];
    highlightImage = [[NSImage alloc] initWithContentsOfFile:[bundle pathForResource:@"icon-alt" ofType:@"png"]];
    
    // Set up FileManager to get .CustomDock items.
    NSFileManager *filemng = [[NSFileManager alloc] init];
    
    
    NSString *username = NSUserName();
    NSString *homedirectory = NSHomeDirectoryForUser(username);
    NSMutableString *customDocksFolder = [NSMutableString string];
    [customDocksFolder appendString: homedirectory];
    [customDocksFolder appendString:@"/.CustomDocks/"];
    
    if ( ![filemng changeCurrentDirectoryPath:customDocksFolder] ) {
        NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
        NSString *exampleAlertSuppress = @"ExampleAlertSuppress";
        
        NSAlert *alert = [[NSAlert alloc] init];
        [alert setMessageText:@"Hey! You're New!"];
        NSString *text = [NSString string];
        text = @"And that's okay! This is apparently you're first launch! Congrats!\nPretty much the first thing you should do is open\nthe preferences so you can make the different docks you want to.\nDon't worry though, it's a painless process. I promise\n\n-ThisIsAnApp"; 
        [alert setInformativeText:text];
        // [alert setShowsSuppressionButton:YES];
        [alert runModal];
        if ([[alert suppressionButton] state] == NSOnState) {
            // Suppress this alert from now on.
            [defaults setBool:YES forKey:exampleAlertSuppress];
        }
        [alert release];
        
        [filemng createDirectoryAtPath:customDocksFolder withIntermediateDirectories:NO attributes:nil error:nil];
        [filemng changeCurrentDirectoryPath:customDocksFolder];
    }
    
    [dockFolderNames addObjectsFromArray:[filemng contentsOfDirectoryAtPath:[filemng currentDirectoryPath] error:nil] ];
    
    
    
//    NSLog(@"FM: %@", [filemng currentDirectoryPath]);
//    
//    NSLog(@"ObjectAt1: %@", [dockFolderNames objectAtIndex:1]);
//    
//    for ( int i = 0; i < [dockFolderNames count]; i++){
//        NSLog(@"Listed: %@", [dockFolderNames objectAtIndex:i]);
//    }
    
    [statusItem setImage:statusImage];
    [statusItem setAlternateImage:highlightImage];
    [statusItem setMenu:theMenu];    
    [statusItem setToolTip:@"STOP POKING ME!"];
    
    NSMutableArray *menuItems = [NSMutableArray array];
    int i = 0;
    if ( i < [dockFolderNames count] ) { 
        if ( [dockFolderNames objectAtIndex:0] != nil ) { 
            NSMenuItem *menuItem = [[NSMenuItem alloc] initWithTitle:[dockFolderNames objectAtIndex:0] action:@selector(dockOne:) keyEquivalent:(@"")];
            [menuItems addObject:menuItem];
            [theMenu insertItem:menuItem atIndex:0+2];
        }
        i++;
    }
    if ( i < [dockFolderNames count] ) { 
        if ( [dockFolderNames objectAtIndex:1] != nil ) { 
            NSMenuItem *menuItem = [[NSMenuItem alloc] initWithTitle:[dockFolderNames objectAtIndex:1] action:@selector(dockTwo:) keyEquivalent:(@"")];
            [menuItems addObject:menuItem];
            [theMenu insertItem:menuItem atIndex:1+2];
        }
        i++;
    }
    if ( i < [dockFolderNames count] ) {
        if ( [dockFolderNames objectAtIndex:2] != nil ) { 
            NSMenuItem *menuItem = [[NSMenuItem alloc] initWithTitle:[dockFolderNames objectAtIndex:2] action:@selector(dockThree:) keyEquivalent:(@"")];
            [menuItems addObject:menuItem];
            [theMenu insertItem:menuItem atIndex:2+2];
        }
        i++;
    }
    if ( i < [dockFolderNames count] ){ 
        if ([dockFolderNames objectAtIndex:3] != nil ) { 
            NSMenuItem *menuItem = [[NSMenuItem alloc] initWithTitle:[dockFolderNames objectAtIndex:3] action:@selector(dockFour:) keyEquivalent:(@"")];
            [menuItems addObject:menuItem];
            [theMenu insertItem:menuItem atIndex:3+2];
        }
        i++;
    }
    if ( i < [dockFolderNames count] ) { 
        if ( [dockFolderNames objectAtIndex:4] != nil ) { 
            NSMenuItem *menuItem = [[NSMenuItem alloc] initWithTitle:[dockFolderNames objectAtIndex:4] action:@selector(dockFive:) keyEquivalent:(@"")];
            [menuItems addObject:menuItem];
            [theMenu insertItem:menuItem atIndex:4+2];
        }
        i++;
    }
}


- (IBAction)dockOne:(id)sender {
    NSString *str = @"cp ~/.CustomDocks/";
    str = [str stringByAppendingString:@"\""];
    str = [str stringByAppendingString:[dockFolderNames objectAtIndex:0]];
    str = [str stringByAppendingString:@"\""];
    NSString *strlong = [ str stringByAppendingString: [NSString stringWithFormat:@"/com.apple.dock.plist ~/Library/Preferences/"] ];
    NSString *string = strlong;
    system([string UTF8String]);
    system("killall Dock");
}
- (IBAction)dockTwo:(id)sender {
    NSString *str = @"cp ~/.CustomDocks/";
    str = [str stringByAppendingString:@"\""];
    str = [str stringByAppendingString:[dockFolderNames objectAtIndex:1]];
    str = [str stringByAppendingString:@"\""];
    NSString *strlong = [ str stringByAppendingString: [NSString stringWithFormat:@"/com.apple.dock.plist ~/Library/Preferences/"] ];
    NSString *string = strlong;
    system([string UTF8String]);
    system("killall Dock");
}
- (IBAction)dockThree:(id)sender {
    NSString *str = @"cp ~/.CustomDocks/";
    str = [str stringByAppendingString:@"\""];
    str = [str stringByAppendingString:[dockFolderNames objectAtIndex:2]];
    str = [str stringByAppendingString:@"\""];
    NSString *strlong = [ str stringByAppendingString: [NSString stringWithFormat:@"/com.apple.dock.plist ~/Library/Preferences/"] ];
    NSString *string = strlong;
    system([string UTF8String]);
    system("killall Dock");
}
- (IBAction)dockFour:(id)sender {
    NSString *str = @"cp ~/.CustomDocks/";
    str = [str stringByAppendingString:@"\""];
    str = [str stringByAppendingString:[dockFolderNames objectAtIndex:3]];
    str = [str stringByAppendingString:@"\""];
    NSString *strlong = [ str stringByAppendingString: [NSString stringWithFormat:@"/com.apple.dock.plist ~/Library/Preferences/"] ];
    NSString *string = strlong;
    system([string UTF8String]);
    system("killall Dock");
}
- (IBAction)dockFive:(id)sender {
    NSString *str = @"cp ~/.CustomDocks/";
    str = [str stringByAppendingString:@"\""];
    str = [str stringByAppendingString:[dockFolderNames objectAtIndex:4]];
    str = [str stringByAppendingString:@"\""];
    NSString *strlong = [ str stringByAppendingString: [NSString stringWithFormat:@"/com.apple.dock.plist ~/Library/Preferences/"] ];
    NSString *string = strlong;
    system([string UTF8String]);
    system("killall Dock");
}

- (IBAction)quitApp:(id)sender {    
    [dockFolderNames release];
    [statusItem release];
    [highlightImage release];
    
    [NSApp terminate:self];
}

@end
