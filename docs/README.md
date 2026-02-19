# MrMeow User Guide

![img.png](img.png)

MrMeow, a chat bot that helps keep track of your day to day tasks!

## Adding Tasks

Command Format: todo <Task_Name>

Example: todo nap 

Task will be added into your Task List!
```
Added: [T][] nap
XX Tasks in list.
```

## Adding deadlines

Command Format: deadline <Task_Name> /by <YYYY-MM-DD>

Example: deadline Assignment /by 2026-12-12

DeadLine Task will be added into your Task List!
```
Added: [D][] assignment || Deadline: 2026-12-12
XX Tasks in list.
```

## Adding Events

Command Format: event <Task_Name> /from <YYYY-MM-DD> /to <YYYY-MM-DD>

Example: event tutorial /from 2026-12-12 /to 2026-12-13

Event Task will be added into your Task List!
```
Added: [E][] tutorial || From: 2026-12-12 To: 2026-12-12
XX Tasks in list.
```

## List all list contents

Command Format: list


## Mark Done or Not Done

Command Format: mark <index_of_task>

Command Format: unmark <index_of_task>

Example: mark 1
```
Done: [T][X] nap
```

## Delete Task

Command Format: delete <index_of_task>

Example: delete 1
```
Done: Removed [T][X] nap
```
## Duplicate Task Warning

MrMeow will warn you of conflicting Task in the Task List.


## Duplicate Task Remove

Command Format: removedupe

Example: removedupe
```
Removed!
```

```
No Duplicate Found!
```

## Find Task by Keyword
Command Format: find <Task_Name>

Example: find nap
```
1. [T][] nap
```

```
No Task Found.
```


