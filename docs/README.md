# Demeter User Guide

**Demeter** is a simple task manager chatbot that helps you keep track of your todos, deadlines, and events. 
You can add tasks, mark them as done, delete them, search for keywords, and even undo your last action. 
It’s built for use via a command-line interface (CLI), allowing for quick commands and fast replies.


![product screenshot](https://github.com/yellowbrickkode/ip/blob/master/docs/Ui.png)

## Command Summary 
| Action               | Format                                                 |
|----------------------|--------------------------------------------------------|
| View list            | `list`                                                 |
| Add to-do            | `todo TASK_DESCRIPTION`                                |
| Add deadline task    | `deadline TASK_DESCRIPTION /by DUE_DATE`               |
| Add event            | `event TASK_DESCRIPTION /from START_DATE /to END_DATE` |
| Mark task            | `mark TASK_INDEX`                                      |
| Unmark task          | `unmark TASK_INDEX`                                    |
| Filter list          | `find KEYWORD`                                         |
| Delete task          | `delete TASK_INDEX`                                    |
| Undo previous action | `undo`                                                 |



## Feature Descriptions:

Note: Words in UPPER_CASE are the parameters to be supplied by the user.

### Listing all tasks: `list`

Shows a list of all tasks in the tasklist.

Format: `list`

### Adding a Todo task: `todo`

Adds a Todo task to the tasklist.

Format: `todo TASK_DESCRIPTION`

Example: `todo watch documentary`

### Adding a Deadline task: `deadline`

Adds a Deadline task to the tasklist.

Format: `deadline TASK_DESCRIPTION /by DUE_DATE`

Example: `deadline submit essay /by 2026-02-23`

### Adding an Event task: `event`

Adds an Event task to the tasklist.

Format: `event TASK_DESCRIPTION /from START_DATE /to END_DATE`

Example: `event business trip /from 2026-04-02 /to 2026-04-05`

### Marking a task completed: `mark`

Marks a task as complete.

Format: `mark TASK_INDEX`

Example: `mark 2`

### Marking a task incomplete: `unmark`

Marks a task as incomplete.

Format: `unmark TASK_INDEX`

Example: `unmark 3`

### Filtering tasks by name: `find`

Displays a list of tasks from the tasklist that contain the keyword in the description.

Format: `find KEYWORD`

Example: `find write`

### Deleting a task: `delete`

Deletes a task from the tasklist.

Format: `delete TASK_INDEX`

Example: `delete 1`

### Undoing the previous action: `undo`

Reverts the tasklist to its previous state.

Format: `undo`
