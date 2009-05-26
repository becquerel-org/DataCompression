package DataCompression.util;

/** Pair of two elements of the same type.
Code from: http://groups.google.com/group/comp.lang.java.help/browse_thread/thread/f8b63fc645c1b487/1d94be050cfc249b
*/
public class Pair <T> 
{ 
   private final T first; 
   private final T second; 
   private transient final int hash; 

   public Pair( T first, T second ) 
   { 
    this.first = first; 
    this.second = second; 
    hash = (first == null? 0 : first.hashCode() * 31) 
          +(second == null? 0 : second.hashCode()); 
   } 

   public T getFirst() 
   { 
    return first; 
   } 
   public T getSecond() 
   { 
    return second; 
   } 

   public int hashCode() 
   { 
    return hash; 
   } 

   public boolean equals( Pair<T> other ) 
   { 
    if ( this == other ) 
    { 
      return true; 
    } 
    return (first == null? other.first == null : first.equals( other.first )) 
     && (second == null? other.second == null : second.equals( other.second )); 
   }
}
