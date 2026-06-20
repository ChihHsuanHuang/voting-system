<template>
  <div class="container">
    <h1>玉山工程師甄選技術測驗考卷</h1>
    <h2>線上投票系統</h2>

    <!-- 投票項目清單 -->
    <section class="vote-section">
      <h3>投票項目</h3>
      <ul>
        <li v-for="item in items" :key="item.id" class="item">
          <span>{{ item.name }} ({{ item.votes }}票)</span>
          <div class="actions">
            <button @click="vote(item.id)">投票</button>
            <button @click="deleteItem(item.id)">刪除</button>
          </div>
        </li>
      </ul>
    </section>

    <!-- 投票人輸入 -->
    <section class="form-section">
      <label>投票人：</label>
      <input v-model="voter" placeholder="輸入投票人" />
    </section>

    <!-- 新增項目 -->
    <section class="form-section">
      <label>新增項目：</label>
      <input v-model="newItem" placeholder="新項目名稱" />
      <button @click="addItem">新增項目</button>
    </section>
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
        const response = await axios.get("http://localhost:8080/api/items");
        this.items = response.data;
      } catch (error) {
        console.error(error);
      }
    },
    async addItem() {
      try {
        await axios.post("http://localhost:8080/api/items", { name: this.newItem });
        this.newItem = "";
        await this.loadItems();
      } catch (error) {
        console.error(error);
      }
    },
    async vote(itemId) {
      try {
        await axios.post("http://localhost:8080/api/vote", {
          voterName: this.voter,
          voteItemId: itemId
        });
        await this.loadItems();
      } catch (error) {
        console.error(error);
      }
    },
    async deleteItem(id) {
      try {
        await axios.delete(`http://localhost:8080/api/items/${id}`);
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
  background: #f9f9f9;
  padding: 20px;
}

.container {
  max-width: 600px;
  margin: auto;
  background: #fff;
  padding: 20px;
  border-radius: 6px;
  box-shadow: 0 2px 6px rgba(0,0,0,0.1);
}

h1, h2 {
  margin: 0;
  text-align: center;
}

.vote-section ul {
  list-style: none;
  padding: 0;
}

.item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: #f1f1f1;
  margin-bottom: 8px;
  padding: 8px;
  border-radius: 4px;
}

.actions button {
  margin-left: 8px;
}

.form-section {
  margin-top: 15px;
}

input {
  padding: 6px;
  margin-left: 5px;
}
</style>
