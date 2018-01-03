var budgetController = (function () {
    var Expense = function (id, description, value) {
        this.id = id;
        this.description = description;
        this.value = value;
        this.percentage = -1;
    };

    Expense.prototype.calcPercentage = function (totalIncome) {
        if (totalIncome > 0) {
            this.percentage = Math.round((this.value / totalIncome) * 100);
        } else {
            this.percentage = -1;
        }
    };

    Expense.prototype.getPercentage = function () {
        return this.percentage;
    };

    var Income = function (id, description, value) {
        this.id = id;
        this.description = description;
        this.value = value;
    };

    var calculateTotal = function (type) {
        var sum = 0;

        data.allItems[type].forEach(function (cur) {
            sum += cur.value;
        });

        data.totals[type] = sum;
    };

    var data = {
        allItems: {
            exp: [],
            inc: [],
        },
        totals: {
            exp: 0,
            inc: 0
        },
        budget: 0,
        percentage: -1,
    };

    return {
        addItem: function (type, desc, val) {
            var newItem;
            var id;
            var lnItems = data.allItems[type].length;
            //Create a new id.
            if (lnItems > 0) {
                id = data.allItems[type][lnItems - 1].id + 1;
            } else {
                id = 0;
            }

            //Create a new expense or income
            if (type === 'exp') {
                newItem = new Expense(id, desc, val);
            } else if (type === 'inc') {
                newItem = new Income(id, desc, val);
            }

            data.allItems[type].push(newItem);

            return newItem;
        },

        deleteItem: function (type, id) {
            var ids;
            var index;

            var ids = data.allItems[type].map(function (current) {
                return current.id;
            });

            index = ids.indexOf(id);

            if (ids !== -1) {
                data.allItems[type].splice(index, 1);
            }
        },

        calculatBudget: function () {
            //Calculate the total incomes and expenses.
            calculateTotal('inc');
            calculateTotal('exp');

            //Calculate the budget
            data.budget = data.totals.inc - data.totals.exp;

            //Calculate the percentage of income spent.
            if (data.totals.inc > 0) {
                data.percentage = Math.round((data.totals.exp / data.totals.inc) * 100);
            } else {
                data.percentage = -1;
            }
        },

        calculatePercentages: function () {
            data.allItems.exp.forEach(function (current) {
                current.calcPercentage(data.totals.inc);
            });
        },

        getPercentages: function () {
            var allPerc = data.allItems.exp.map(function (cur) {
                return cur.getPercentage();
            });

            return allPerc;
        },

        getBudget: function () {
            return {
                budget: data.budget,
                totalInc: data.totals.inc,
                totalExp: data.totals.exp,
                percentage: data.percentage
            };
        },

        testing: function () {
            return data;
        },
    };
})();

var UIController = (function () {
    var DOMstrings = {
        inputType: '.add__type',
        inputDescription: '.add__description',
        inputValue: '.add__value',
        inputBtn: '.add__btn',
        incomeContainer: '.income__list',
        expenseContainer: '.expenses__list',
        budgetLabel: '.budget__value',
        incomeLabel: '.budget__income--value',
        expenseLabel: '.budget__expenses--value',
        percentageLabel: '.budget__expenses--percentage',
        container: '.container',
        expensesPercLabel: '.item__percentage',
        monthLabel: '.budget__title--month'
    };

    var formatNumer = function (num, type) {
        var numSplit;
        var int;
        var dec;

        num = Math.abs(num);
        num = num.toFixed(2);
        numSplit = num.split('.');
        int = numSplit[0];
        dec = numSplit[1];

        if (int.length > 3) {
            int = int.substr(0, int.length - 3) + ',' + int.substr(int.length - 3, 3);
        }

        return (type === 'exp' ? '-' : '+') + ' ' + int + '.' + dec;
    };

    var nodeListForEach = function (list, callback) {
        var lnList = list.length;

        for (var i = 0; i < lnList; i++) {
            callback(list[i], i);
        }
    };

    return {
        getInput: function () {
            //Get the type of descripcion, inc -> income, exp -> expensive
            return {
                type: document.querySelector(DOMstrings.inputType).value,
                //Get the descripcion
                description: document.querySelector(DOMstrings.inputDescription).value,
                //Get the value.
                value: parseFloat(document.querySelector(DOMstrings.inputValue).value),
            };
        },

        addListItem: function (obj, type) {
            var html;
            var element;

            //Create html to add new item
            if (type === 'inc') {
                element = DOMstrings.incomeContainer;

                html = `<div class="item clearfix" id="inc-${obj.id}">
                            <div class="item__description">${obj.description}</div>
                            <div class="right clearfix">
                                <div class="item__value">${formatNumer(obj.value, type)}</div>
                                <div class="item__delete">
                                    <button class="item__delete--btn"><i class="ion-ios-close-outline"></i></button>
                                </div>
                            </div>
                        </div>`;
            } else if (type === 'exp') {
                element = DOMstrings.expenseContainer;

                html = `<div class="item clearfix" id="exp-${obj.id}">
                            <div class="item__description">${obj.description}</div>
                            <div class="right clearfix">
                                <div class="item__value">${formatNumer(obj.value, type)}</div>
                                <div class="item__percentage">21%</div>
                                <div class="item__delete">
                                    <button class="item__delete--btn"><i class="ion-ios-close-outline"></i></button>
                                </div>
                            </div>
                        </div>`;
            }

            //Insert the html as the last child of the container of each type item.
            document.querySelector(element).insertAdjacentHTML('beforeend', html);
        },

        deleteListItem: function (selectorId) {
            var elem = document.getElementById(selectorId);
            elem.parentNode.removeChild(elem);
        },

        clearFields: function () {
            var fields;
            var fieldsArr;

            fields = document.querySelectorAll(DOMstrings.inputDescription + ', ' + DOMstrings.inputValue);
            fieldsArr = Array.prototype.slice.call(fields);

            fieldsArr.forEach(function (current, index, array) {
                current.value = "";
            });

            fields[0].focus();
        },

        displayBudget: function (obj) {
            var type = (obj.budget > 0) ? 'inc' : 'exp';

            document.querySelector(DOMstrings.budgetLabel).textContent = formatNumer(obj.budget, type);
            document.querySelector(DOMstrings.incomeLabel).textContent = formatNumer(obj.totalInc, 'inc');
            document.querySelector(DOMstrings.expenseLabel).textContent = formatNumer(obj.totalExp, 'exp');

            if (obj.percentage > 0) {
                document.querySelector(DOMstrings.percentageLabel).textContent = obj.percentage + '%';
            } else {
                document.querySelector(DOMstrings.percentageLabel).textContent = '-';
            }
        },

        displayPercentages: function (percentages) {
            var fields = document.querySelectorAll(DOMstrings.expensesPercLabel);

            nodeListForEach(fields, function (current, index) {
                if (percentages[index] > 0) {
                    current.textContent = percentages[index] + '%';
                } else {
                    current.textContent = '-';
                }
            });
        },

        displayMonth: function () {
            var now;
            var month;
            var year;
            var months;

            months = ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'];

            now = new Date();
            month = now.getMonth();
            year = now.getFullYear();

            document.querySelector(DOMstrings.monthLabel).textContent = months[month] + ' ' + year;
        },

        changedType: function () {
            var fields = document.querySelectorAll(
                DOMstrings.inputType + ', ' +
                DOMstrings.inputDescription + ', ' +
                DOMstrings.inputValue
            );

            nodeListForEach(fields, function (cur) {
                cur.classList.toggle('red-focus');
            });

            document.querySelector(DOMstrings.inputBtn).classList.toggle('red');
        },

        getDOMstrings: function () {
            return DOMstrings;
        },
    };
})();

var controller = (function (budgetCtrl, UICtrl) {
    //Funtion to initialize the event listeners.
    var setupEventListeners = function () {
        //Get the selectors of dom objects.
        var DOM = UICtrl.getDOMstrings();

        document.querySelector(DOM.inputBtn).addEventListener('click', ctrlAddItem);

        document.addEventListener('keypress', function (event) {
            if (event.keyCode === 13 || event.which === 13) {
                ctrlAddItem();
            }
        });

        document.querySelector(DOM.container).addEventListener('click', ctrlDeleteItem);
        document.querySelector(DOM.inputType).addEventListener('change', UICtrl.changedType);
    };

    var updateBudget = function () {
        //Calculate the budget
        budgetCtrl.calculatBudget();

        //Get the budget calculated.
        var budget = budgetCtrl.getBudget();

        //Display the total budget in the view.
        UICtrl.displayBudget(budget);
    };

    var updatePercentages = function () {
        //Calculate percentages.
        budgetCtrl.calculatePercentages();

        //Get the percentages calculated.
        var percentages = budgetCtrl.getPercentages();

        //Display the percentages calculated.
        UICtrl.displayPercentages(percentages);
    };

    //Function that add the new item to the budget, execute when the bottom or the enter key is pressed.
    var ctrlAddItem = function () {
        var input;
        var newItem;

        //Get the input data.
        input = UICtrl.getInput();

        if (input.description !== "" && !isNaN(input.value) && input.value > 0) {
            //Add the new item to the budget.
            newItem = budgetCtrl.addItem(input.type, input.description, input.value);

            //Add the new item to the view
            UICtrl.addListItem(newItem, input.type);

            //Clear the input fields.
            UICtrl.clearFields();

            //Calculate and update budget.
            updateBudget();

            //Calculate and update percentages.
            updatePercentages();
        }
    };

    var ctrlDeleteItem = function (event) {
        var itemId;
        var splitId;
        var type;
        var id;

        itemId = event.target.parentNode.parentNode.parentNode.parentNode.id;


        if (itemId) {
            splitId = itemId.split('-');
            type = splitId[0];
            id = parseInt(splitId[1]);

            //Delete the item from the data structure.
            budgetCtrl.deleteItem(type, id);

            //Delete item from the view.
            UICtrl.deleteListItem(itemId);

            //Calculate and update budget.
            updateBudget();

            //Calculate and update percentages.
            updatePercentages();
        }
    };

    return {
        init: function () {
            console.log('Application started');
            UICtrl.displayMonth();
            UICtrl.displayBudget({
                budget: 0,
                totalInc: 0,
                totalExp: 0,
                percentage: 0
            });
            setupEventListeners();
        },
    };

})(budgetController, UIController);

controller.init();
