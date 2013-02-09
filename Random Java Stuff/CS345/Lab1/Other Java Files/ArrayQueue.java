public ArrayQueue implements QueueADT
{

    public ArrayQueue
    {
        arr = new Object [DEFAULT_SIZE];
        front = 0;
        back = arr.length - 1;
    }

////////////////////   PRIVATE METHODS ///////////////////////////////////
    private boolean isFull()
    {
        // This keeps a buffer slot open by checking if back + 2 is equal to front
        // 
        boolean full = ( (back + 2) % arr.length) == front
        return full;
    }
    
    private void growArray()
    {
        Object[] newArr = new Object[ arr.length + growArray ];
        int i = newArr.length -1;
        if ( !isEmpty() ) {
            i = 0;
            while ( front != back ) {
                newArr[i] = arr[front];
                front = (front + 1) % arr.length;
                i++;
            }
            newArr[i] = arr[front];
        }
    }
    
//////////////////////////// PUBLIC METHODS /////////////////////////////////////
    public Object add( Object newItem )
    {
        if ( newItem ==  null ) return null;
        
        if ( isFull() ) growArray();
        
        back++;
        if ( back == arr.length ) back = 0;
        
        arr[back] = newItem;
        return newItem;
    }
    
    public Object remove()
    {
        if ( isEmpty() ) return null;
        
        Object item = arr[front];
        arr[front] = null;
        front = ( front + 1 ) % arr.length;
        
        return item;
    }
    
    
    
    
    
    
    
    public Object front()
    {
        if ( isEmpty() ) {
            return null;
        }
        else {
            return arr[front];
        }
    }
        
    public Object back()
    {
        if ( isEmpty() ) {
            return null;
        }
        else {
            return arr[back];
        }
    }

    public boolean isEmpty()
    {
        return (front == (back + 1) % arr.length);
    }



// % - modulus will make it so it returns the remainder of division.
back = (back + 1) % arr.length
// empty condition
front == (back + 1) % arr.length
