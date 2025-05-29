<script setup lang="ts">
import {
  createColumnHelper,
  FlexRender,
  getCoreRowModel,
  getFilteredRowModel,
  getSortedRowModel,
  type SortingState,
  useVueTable,
} from '@tanstack/vue-table'
import { computed, onMounted, ref } from 'vue'
import readXlsxFile from 'read-excel-file'

onMounted(() => {
  const input = document.getElementById('fileInput') as HTMLInputElement
  return input.addEventListener('change', () => {
    if (input.files?.length) {
      readXlsxFile(input.files[0]).then((rows) => {
        const keys = [...rows[0]]
        data.value = rows
          .slice(1)
          .map<Data>((row) =>
            Object.fromEntries(keys.map((key, i) => [key, row[i]?.toString() ?? ''])),
          )
      })
    }
  })
})

interface Data {
  Nachname: string
  Vorname: string
  Firma: string
  Ort: string
  PLZ: string
  'E-Mail-Adresse': string
}

const columnHelper = createColumnHelper<Data>()

const columns = [
  columnHelper.accessor('Vorname', {
    enableColumnFilter: false,
  }),
  columnHelper.accessor('Nachname', { enableColumnFilter: false }),
  columnHelper.accessor('Firma', { enableColumnFilter: false }),
  columnHelper.accessor('Ort', { enableColumnFilter: false }),
  columnHelper.accessor('PLZ', {
    enableColumnFilter: true,
  }),
  columnHelper.accessor('E-Mail-Adresse', {
    enableColumnFilter: false,
  }),
]

const data = ref<Data[]>([])

const sorting = ref<SortingState>([])

const table = useVueTable({
  get data() {
    return data.value
  },
  columns,
  state: {
    get sorting() {
      return sorting.value
    },
  },
  onSortingChange: (updaterOrValue) => {
    sorting.value =
      typeof updaterOrValue === 'function' ? updaterOrValue(sorting.value) : updaterOrValue
  },
  getCoreRowModel: getCoreRowModel(),
  getSortedRowModel: getSortedRowModel(),
  getFilteredRowModel: getFilteredRowModel(),
  debugTable: true,
  initialState: {
    sorting: [
      {
        id: 'PLZ',
        desc: false,
      },
    ],
  },
})

const mailTo = computed(() => {
  const to = table
    .getFilteredRowModel()
    .rows.map((row) => row.getValue('E-Mail-Adresse'))
    .join(',')
  return `mailto:${to}`
})

const openMail = () => window.open(mailTo.value)

const setPLZFilter = (event: InputEvent) => {
  const value = (event.target as HTMLInputElement).value
  console.log(value)
  table.getColumn('PLZ')?.setFilterValue(value)
  console.log(table.getFilteredRowModel().rows.map((row) => row.getValue('PLZ')))
}
</script>

<template>
  <div class="flex flex-col gap-7 p-5 h-full">
    <div class="flex gap-5 items-center">
      <span class="text-bold">Datei auswählen: </span>
      <input
        class="border border-gray-500 rounded p-3"
        id="fileInput"
        type="file"
        accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
      />
      <div>({{ table.getRowModel().rows.length }} Einträge)</div>
    </div>
    <div class="min-h-0 overflow-auto grow border border-slate-500">
      <table class="table-fixed w-full">
        <thead>
          <tr v-for="headerGroup in table.getHeaderGroups()" :key="headerGroup.id">
            <th
              class="p-2 sticky top-0 bg-slate-300"
              v-for="header in headerGroup.headers"
              :key="header.id"
              :colSpan="header.colSpan"
              :class="header.column.getCanSort() ? 'cursor-pointer select-none' : ''"
              @click="header.column.getToggleSortingHandler()?.($event)"
            >
              <div class="flex gap-3 items-center">
                <FlexRender :render="header.column.columnDef.header" :props="header.getContext()" />

                <div class="w-4">
                  {{ { asc: ' 🔼', desc: ' 🔽' }[header.column.getIsSorted() as string] }}
                </div>

                <template v-if="header.column.getCanFilter()">
                  <input
                    @click="(event) => event.stopPropagation()"
                    @input="(event) => setPLZFilter(event as InputEvent)"
                    class="font-normal border rounded"
                    size="5"
                  />
                </template>
              </div>
            </th>
          </tr>
        </thead>

        <tbody>
          <tr
            class="border-b border-b-gray-200"
            v-for="row in table.getRowModel().rows"
            :key="row.id"
          >
            <td class="p-2" v-for="cell in row.getVisibleCells()" :key="cell.id">
              <FlexRender :render="cell.column.columnDef.cell" :props="cell.getContext()" />
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <button @click="openMail" class="rounded p-3 border border-slate-500 bg-slate-300">
      Email an sichtbare Adressen senden
    </button>
  </div>
</template>
