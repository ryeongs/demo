<template>
  <li>
    <label>
      <input type="checkbox" :checked="todo.done" @change="handleCheck(todo)" />
      <span
        v-show="!todo.editing"
        :class="todo.done ? 'title-finish' : 'title-normal'">
        {{ todo.title }}
      </span>
      <input
        class="todo-edit"
        type="text"
        v-show="todo.editing"
        :value="todo.title"
        ref="inputRef"
        @blur="handleBlur(todo, $event)"
        @keyup.enter="handleBlur(todo, $event)" />
    </label>
    <button class="btn btn-danger" @click="handleDelete(todo.id)">Delete</button>
    <button class="btn btn-edit" @click="handleEdit(todo)">Edit</button>
  </li>
</template>

<script>
  export default {
    name: 'AppItem',
    props: ['todo'],
    methods: {
      handleCheck(todo) {
        todo.done = !todo.done
        this.$store.commit('updateTodo', todo)
      },
      handleEdit(todo) {
        if (Object.prototype.hasOwnProperty.call(todo, 'editing')) {
          todo.editing = true
        } else {
          this.$set(todo, 'editing', true)
        }
        this.$nextTick(function () {
          this.$refs.inputRef.focus()
        })
      },
      handleBlur(todo, e) {
        todo.editing = false
        if (!e.target.value.trim()) return alert('！')
        todo.title = e.target.value
        this.$store.commit('updateTodo', todo)
      },
      handleDelete(id) {
        this.$store.commit('deleteTodo', id)
      }
    }
  }
</script>

<style scoped>
  /*item*/
  li {
    list-style: none;
    height: 36px;
    line-height: 36px;
    padding: 0 5px;
    border-bottom: 1px solid #ddd;
  }

  li label {
    float: left;
    cursor: pointer;
  }

  li label input {
    vertical-align: middle;
    margin-right: 6px;
    position: relative;
    top: -1px;
  }

  li button {
    float: right;
    display: none;
    margin-top: 3px;
  }

  li:before {
    content: initial;
  }

  li:last-child {
    border-bottom: none;
  }

  li:hover {
    background-color: #ddd;
  }

  li:hover button {
    display: block;
  }

  .title-normal {
    color: black;
  }

  .title-finish {
    color: gray;
    text-decoration: line-through;
  }

  .todo-edit {
    border: none;
    outline: none;
    /* background-color: #ddd; */
    font-size: 16px;
  }
</style>
