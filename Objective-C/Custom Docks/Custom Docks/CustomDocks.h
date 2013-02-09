//
//  CustomDocks.h
//  Custom Docks
//
//  Created by Daniel Miedema on 1/17/12.
//  Copyright (c) 2012 ThisIsAnApp. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "TIIAAppDelegate.h"
#import "Preferences.h"

@interface CustomDocks : NSObject {

    NSStatusItem *statusItem;
    IBOutlet NSMenu *theMenu;
    NSWorkspace *workspace;
    NSImage *statusImage;
    NSImage *highlightImage;
    NSMutableArray *dockFolderNames;
}

- (IBAction)displayFirstLaunchHelp:(id)sender;
- (IBAction)modifyCustomDocks:(id)sender;

- (IBAction)dockOne:(id)sender;
- (IBAction)dockTwo:(id)sender;
- (IBAction)dockThree:(id)sender;
- (IBAction)dockFour:(id)sender;
- (IBAction)dockFive:(id)sender;

- (IBAction)quitApp:(id)sender;

- (void) enableWorkDock;
- (void) enableNormalDock;
- (void) enableDevDock;

@end
