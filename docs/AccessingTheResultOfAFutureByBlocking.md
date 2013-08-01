
# Accessing the result of a Future by blocking

* When the main thread of an application creates a future, it is possible for it to complete before the thread running the future executes the future's computation.

* In the below example, we put the main thread to sleep, while we wait for the Future to complete

Example - Accessing_the_result_of_a_future




