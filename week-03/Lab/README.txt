# This README contains instructions to be read and executed with Java file IO.
# First, open this file (via Java) and read its contents.
# Lines that start with '#' are comments. Ignore them.
# Lines that start with CREATE are followed by a local file path for a file to be created.
# Like so:
CREATE elements.data
# When you see the above command, you should create a new file called "elements.data".
# Lines that start with APPEND are followed by two things:
#   1. a local file path
#   2. content to be appended to the specified file
APPEND elements.data hydrogen
# When you see the above command, append the value "hydrogen" to the file "elements.data"
# Keep going.
APPEND elements.data helium
APPEND elements.data lithium
# Blank lines should be ignored as well.

# More practice.
CREATE bad-file.txt
APPEND bad-file.txt Oh, wow.
APPEND bad-file.txt This is a bad file.
APPEND bad-file.txt It should be deleted.

CREATE super-bad-file.java

# Lines that start with DELETE are followed by a local file path for a file to be deleted.
DELETE bad-file.txt
# When you see the above command, delete the file identified by the path.
# If the file-to-be-deleted doesn't exist, don't let your program crash! Check before you delete.
DELETE super-bad-file.java
DELETE this-file-does-not-exist.txt

# Lines that start with COPY are followed by two things:
#   1. a local file path - the source path
#   2. another local file path - the destination.
# Copy the contents of the source file into the destination file.
# If the destination doesn't exist, create it.
COPY elements.data destination/new-elements-file.txt

# Keep going!
CREATE another-elements.csv
APPEND another-elements.csv beryllium, boron, carbon
APPEND another-elements.csv nitrogen, oxygen, fluorine
APPEND another-elements.csv earth, air, water, aether
COPY another-elements.csv destination/new-elements-file.txt
DELETE another-elements.csv

# Double-check your files and directories. Confirm they're correct.

# elements.data:
# hydrogen
# helium
# lithium

# destination/new-elements-file.txt:
# hydrogen
# helium
# lithium
# beryllium, boron, carbon
# nitrogen, oxygen, fluorine
# earth, air, water, aether