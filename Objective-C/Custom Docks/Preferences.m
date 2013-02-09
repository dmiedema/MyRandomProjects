//
//  Preferences.m
//  Custom Docks
//
//  Created by Daniel Miedema on 1/19/12.
//  Copyright (c) 2012 ThisIsAnApp. All rights reserved.
//

#import "Preferences.h"

@implementation Preferences

- (id) init {
    [super init];
    dockFolderNames = [[NSMutableArray alloc] init];
    return self;
}

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        NSFileManager *filemng = [[NSFileManager alloc] init];
        
        
        NSString *username = NSUserName();
        NSString *homedirectory = NSHomeDirectoryForUser(username);
        NSMutableString *customDocksFolder = [NSMutableString string];
        [customDocksFolder appendString: homedirectory];
        [customDocksFolder appendString:@"/.CustomDocks/"];
        
        if ( ![filemng changeCurrentDirectoryPath:customDocksFolder] ) {
            NSLog(@"something went wrong with preferences changing to the correct folder");
        }
        
        [dockFolderNames addObjectsFromArray:[filemng contentsOfDirectoryAtPath:[filemng currentDirectoryPath] error:nil] ];

    }
    
    return self;
}

@end
