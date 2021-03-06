# Pre-work - ToDoApp

ToDoApp is an android app that allows building a todo list and basic todo items management functionality including adding new items, editing and deleting an existing item.

Submitted by: David Phan

Time spent: **X** hours spent in total

## User Stories

The following **required** functionality is completed:

* [X] User can **successfully add and remove items** from the todo list
* [X] User can **tap a todo item in the list and bring up an edit screen for the todo item** and then have any changes to the text reflected in the todo list.
* [X] User can **persist todo items** and retrieve them properly on app restart

The following **optional** features are implemented:

* [X] Persist the todo items [into SQLite](http://guides.codepath.com/android/Persisting-Data-to-the-Device#sqlite) instead of a text file
* [X] Improve style of the todo items in the list [using a custom adapter](http://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView)
* [ ] Add support for completion due dates for todo items (and display within listview item)
* [X] Use a [DialogFragment](http://guides.codepath.com/android/Using-DialogFragment) instead of new Activity for editing items
* [ ] Add support for selecting the priority of each todo item (and display in listview item)
* [X] Tweak the style improving the UI / UX, play with colors, images or backgrounds

The following **additional** features are implemented:

* [ ] List anything else that you can get done to improve the app functionality!

## Video Walkthrough

Here's a walkthrough of implemented user stories:

### Required Functionality
<img src='https://github.com/davidqphan/ToDoApp/blob/master/todoapp-basic-requirements.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

### Optional Functionality
<img src='https://github.com/davidqphan/ToDoApp/blob/master/optional-features.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

## Project Analysis

As part of your pre-work submission, please reflect on the app and answer the following questions below:

**Question 1:** "What are your reactions to the Android app development platform so far? Compare and contrast Android's approach to layouts and user interfaces in past platforms you've used."

**Answer:** There is still alot to learn about Android app development. I cannot comment on the approach as this is my first platform to creating layouts and user interfaces. However I am enjoying the learning process.

**Question 2:** "Take a moment to reflect on the `ArrayAdapter` used in your pre-work. How would you describe an adapter in this context and what is its function in Android? Why do you think the adapter is important? Explain the purpose of the `convertView` in the `getView` method of the `ArrayAdapter`."

**Answer:** ArrayAdapter provides views for a list view,whenever the ListView needs to draw a view at a position of the list view, it gets it from the adapter. They place data and the logic for creating views out of the data. The 'convertView' is a way for the ListView to recycle old View objects that are no longer being used. In 'getView', it returns a View that displays the data at the specified position in the data set. It uses the 'convertView' to check if it can use an old View to reuse, if possible. The ViewGroup parameter where it is the parent that the view will be attached to.

## Notes

Describe any challenges encountered while building the app.

* I had an issue with DBFlow where I was overwriting the data instead of adding a new todo item.

## License

    Copyright [2017] [David Phan]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
