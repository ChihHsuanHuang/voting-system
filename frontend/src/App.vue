<template>
  <div>
    <h1>玉山工程師甄選技術測驗考卷</h1>
    <h1>線上投票系統</h1>

    <h2>投票項目</h2>

    <ul>
      <li
        v-for="item in items"
        :key="item.id"
      >
        {{ item.name }}
        ({{ item.votes }}票)

        <button
          @click="vote(item.id)"
        >
          投票
        </button>

        <button
          @click="deleteItem(item.id)"
        >
          刪除
        </button>

      </li>
    </ul>

    <hr>

    <input
      v-model="voter"
      placeholder="投票人"
    />

    <hr>

    <input
      v-model="newItem"
      placeholder="新項目名稱"
    />

    <button
      @click="addItem"
    >
      新增項目
    </button>

  </div>
</template>

<script>
import axios from "axios";

export default {

  data() {

    return {
      voter: "",
      newItem: "",
      items: []
    };
  },

  mounted() {
    this.loadItems();
  },

  methods: {

    async loadItems() {

      try {

        const response =
          await axios.get(
            "http://localhost:8080/api/items"
          );

        this.items = response.data;

      } catch (error) {

        console.error(error);

      }
    },

    async addItem() {

      try {

        await axios.post(
          "http://localhost:8080/api/items",
          {
            name: this.newItem
          }
        );

        this.newItem = "";

        await this.loadItems();

      } catch (error) {

        console.error(error);

      }
    },

    async vote(itemId) {

      try {

        await axios.post(
          "http://localhost:8080/api/vote",
          {
            voterName: this.voter,
            voteItemId: itemId
          }
        );

        await this.loadItems();

      } catch (error) {

        console.error(error);

      }
    },

    async deleteItem(id) {

      try {

        await axios.delete(
          `http://localhost:8080/api/items/${id}`
        );

        await this.loadItems();

      } catch (error) {

        console.error(error);

      }
    }

  }
};
</script>

<style>

body {
  font-family: Arial, sans-serif;
  padding: 20px;
}

button {
  margin-left: 10px;
}

input {
  margin: 5px;
  padding: 5px;
}

</style>