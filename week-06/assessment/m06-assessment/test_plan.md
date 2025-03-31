# AirBnB Reservations

## WorkFlow

# Features to complete:
## View reservations for Host location 
 -[x]Display all reservations for that host in that location
 -[x]admin will have to enter host email to find them
 -[x]Display locations the host
 -[x]Display guest info{guest name, dates, total}
 -[x]Sort by end date
    ##Validation
 -[w] if host is not found display error message
 -[w] if no reservations display error message
 -[w] email of host is required
## Make a Reservation
 -[x]admin had to enter guest email to search for guest and host email to find host
 -[x]Display locations of said host
 -[w]Display all future reservations so admin knows which dates are available
 -[x]enter start and end date for reservation
 -[w]Calculate total for reservation
 -[x]Calculate the total, display a summary, and ask the administrator to confirm. 
 The reservation total is based on the host's standard rate and weekend rate. 
 For each day in the reservation, determine if it is a weekday (Sunday, Monday, Tuesday, Wednesday, or Thursday) 
 or a weekend (Friday or Saturday). 
 If it's a weekday, the standard rate applies. If it's a weekend, the weekend rate applies.
 -[x]confirm and save reservation
    ##Validation
 -[x] guest host and start end dates are required
 -[x] the guest and host must already exist
 -[x] start date must come before end date
 -[x] reservations cannot overlap
 -[x] start date must be in future
## Edit Reservation
 -[w]Find reservations.
 -[w] Start and end date can be edited,  that is all
 -[x] recalculate the total, display summary, ask the administrator confirm
     ##Validation
 -[x] guest host and start end dates are required
 -[x] the guest and host must already exist
 -[x] start date must come before end date
 -[x] cant move start date to past or overlap
## Cancel Reservation
 -[x]Find and cancel future reservation
 -[x]only future reservations are shown\
 -[x]display success message
    ##Validation
 -[x]you cannot cancel a reservation that start date is in the past.
## Testing
 -[x]Create test data
 -[x]create procedure set_known_good_state()