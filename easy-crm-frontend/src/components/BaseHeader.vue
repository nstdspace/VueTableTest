<script setup lang="ts">
import LoggedInUserPanel from '@/components/LoggedInUserPanel.vue'

defineProps<{
  visibleClientCount: number
}>()
</script>
<template>
  <header class="bg-slate-300 w-full h-16 p-5 gap-10 flex items-center">
    <h1 class="px-3 py-1 rounded-xl bg-slate-400 text-lg font-bold">Kundenverwaltung</h1>
    <span>Angezeigte Einträge: {{ visibleClientCount }}</span>
    <LoggedInUserPanel class="ml-auto" />
  </header>
</template>
