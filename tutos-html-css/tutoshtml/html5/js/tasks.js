$(document).ready(function(){
	$('#add-task-form').on('submit', function(e){
		addTask(e);
	});

	$('#edit-task-form').on('submit', function(e){
		updateTask(e);
	});

	$('#task-table').on('click', '#remove-task', function(){
		var id = $(this).data('id');
		removeTask(id);
	});

	$('#clear-tasks').on('click', function(){
		clearAllTasks();
	});

	// Function to display tasks.
	displayTasks();
	function displayTasks()
	{
		var tasksList = JSON.parse(localStorage.getItem('tasks'));

		if(tasksList != null)
		{
			tasksList = tasksList.sort(sortByTime);

			var i = 0;

			$.each(tasksList, function(key, value){
				$('#task-table').append('<tr id="' + value.id + '">' +
										'<td>' + value.task + '</td>' +
										'<td>' + value.priority_task + '</td>' +
										'<td>' + value.date_task + '</td>' +
										'<td>' + value.time_task + '</td>' +
										'<td><a href="edit.html?id=' + value.id + '" class="btn btn-default">Editar</a> ' +
										'<a href="#" id="remove-task" data-id="' + value.id + '" class="btn btn-danger">Remove</a></td>' +
										'</td>');
			});
		}
	}

	function sortByTime(a, b)
	{
		var aTime = a.time;
		var bTime = b.time;

		return ((aTime < bTime) ? -1 : ((bTime < aTime) ? 1 : 0));
	}

	// Function to add task
	function addTask(e)
	{
		var newDate = new Date();
		id = newDate.getTime();

		var task = $('#task').val();
		var priority = $('#priority').val();
		var date = $('#date').val();
		var time = $('#time').val();

		if(task == '')
		{
			alert('Task is require');
			e.preventDefault();
		}
		else if(date == '')
		{
			alert('Date is require');
			e.preventDefault();
		}
		else if(time == '')
		{
			alert('Time is require');
			e.preventDefault();
		}
		else if(priority == '')
		{
			priority = 'normal';
		}
		else
		{
			tasks = JSON.parse(localStorage.getItem('tasks'));

			if(tasks == null)
			{
				tasks = [];
			}

			var tasksList = JSON.parse(localStorage.getItem('tasks'));

			var newTasks = {
				'id': id,
				'task': task,
				'priority_task': priority,
				'date_task': date,
				'time_task': time
			};

			tasks.push(newTasks);

			localStorage.setItem('tasks', JSON.stringify(tasks));
		}
	}

	// Function to update task.
	function updateTask(e)
	{
		console.log('vvvvvv');
		var id = $('#task_id').val();
		var task = $('#task').val();
		var priority = $('#priority').val();
		var date = $('#date').val();
		var time = $('#time').val();

		if(task == '')
		{
			alert('Task is require');
			e.preventDefault();
		}
		else if(date == '')
		{
			alert('Date is require');
			e.preventDefault();
		}
		else if(time == '')
		{
			alert('Time is require');
			e.preventDefault();
		}
		else if(priority == '')
		{
			priority = 'normal';
		}
		else
		{
			var tasksList = JSON.parse(localStorage.getItem('tasks'));

			// Delete the task before add the changes.
			for(var i=0; i < tasksList.length; i++){
				if(tasksList[i].id == id){
					tasksList.splice(i,1)
				}
				localStorage.setItem('tasks', JSON.stringify(tasksList));
			}

			tasks = JSON.parse(localStorage.getItem('tasks'));

			if(tasks == null)
			{
				tasks = [];
			}

			var newTasks = {
				'id': id,
				'task': task,
				'priority_task': priority,
				'date_task': date,
				'time_task': time
			};

			tasks.push(newTasks);

			localStorage.setItem('tasks', JSON.stringify(tasks));
		}
	}

	function removeTask(id)
	{
		if(confirm('Are you sure you want to delete this task?'))
		{
			var tasksList = JSON.parse(localStorage.getItem('tasks'));

			for(var i=0; i < tasksList.length; i++){
				if(tasksList[i].id == id){
					tasksList.splice(i, 1);
				}
				localStorage.setItem('tasks', JSON.stringify(tasksList));
			}

			location.reload();
		}
	}

	function clearAllTasks()
	{
		if(confirm('Do you want to delete all the tasks?'))
		{
			localStorage.clear();
			location.reload();
		}
	}
});

// Function for getting single task
function getTask(){
	var $_GET = getQueryParams(document.location.search);
	id = $_GET['id'];

	var taskList = JSON.parse(localStorage.getItem('tasks'));

	for(var i=0; i < taskList.length; i++){
		if(taskList[i].id == id){
			$('#edit-task-form #task_id').val(taskList[i].id);
			$('#edit-task-form #task').val(taskList[i].task);
			$('#edit-task-form #priority').val(taskList[i].priority_task);
			$('#edit-task-form #date').val(taskList[i].date_task);
			$('#edit-task-form #time').val(taskList[i].time_task);
		}
	}
}

// Function to Get HTTP GET Requests
function getQueryParams(qs) {
    qs = qs.split("+").join(" ");
    var params = {},
        tokens,
        re = /[?&]?([^=]+)=([^&]*)/g;

    while (tokens = re.exec(qs)) {
        params[decodeURIComponent(tokens[1])]
            = decodeURIComponent(tokens[2]);
    }

    return params;
}