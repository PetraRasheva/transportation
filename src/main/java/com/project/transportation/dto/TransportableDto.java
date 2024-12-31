package com.project.transportation.dto;

public sealed interface TransportableDto permits GoodsDto, PassengersDto {
 /*
 The sealed keyword in Java allows you to define a class or interface whose subclasses are restricted to a specific set of types.
 This ensures that the class hierarchy is controlled and that you can clearly specify which classes are allowed to extend or implement the sealed
 class or interface.
In your case, the sealed keyword is applied to the TransportableDto interface to prevent other classes from arbitrarily implementing it.
Only the classes you explicitly list with the permits clause can implement TransportableDto. This makes the design safer by ensuring that no
other types can implement the interface unless you allow it.
The permits clause defines the specific classes that are allowed to implement or extend the sealed class or interface.
In your case, you are saying that only GoodsDto and PassengersDto are allowed to implement the TransportableDto interface.
Without the permits clause, any class could potentially implement the TransportableDto interface, which would defeat the purpose of restricting
the hierarchy.
  */
}
