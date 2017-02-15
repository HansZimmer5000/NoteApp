# NoteApp (Android 5.0 or higher)
App for saving short notes set with a category.
First tab for creating a new note, second tab with all the notes within a certain category.

A Tab has a category, title and the text.
There are a few categories, but its thought that you can easily add new ones during execution.

# Milestones:
- (Done, Must) App can safe notes persistent on the phone.
- (Done, Must) App can create, list, setUpdatingId and delete single notes.
- (Should) Export the notes (via Mail or something else).
- (Kicked, Can) Include (more or less useful) some functionality with Erlang / Prolog.
- (Can) If export is possible then perhaps an Excel example sheet for better overview on your PC
- (Can) If that Excel sheet is done, the next Step is to let the Excel get the Notes all by its self, so the Excel is always up to date.
- (Can) Safe the notes with something different then SQLite.

# Changelog:
- Change of heart: After a interesting lecture about Kotlin, lets try it out: Kotlin instead of Java.
- Sadly the main data class "Note" should be Kotlin, because there its most effective (short!), but greenDao doesnt work with Kotlin here...
- Kicked Erlang / Prolog functionality, its seems like it is pretty hard to get Erlang working on Android. In Addition, there is nothing to do for Erlang, will write a piece of Erlang in the next project
