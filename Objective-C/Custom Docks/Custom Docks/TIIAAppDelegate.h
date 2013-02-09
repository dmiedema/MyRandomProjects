//
//  TIIAAppDelegate.h
//  Custom Docks
//
//  Created by Daniel Miedema on 1/17/12.
//  Copyright (c) 2012 ThisIsAnApp. All rights reserved.
//

#import <Cocoa/Cocoa.h>

@interface TIIAAppDelegate : NSObject <NSApplicationDelegate> {
     NSMutableArray *dockFolderNames;
}

@property (assign) IBOutlet NSWindow *window;


@end
